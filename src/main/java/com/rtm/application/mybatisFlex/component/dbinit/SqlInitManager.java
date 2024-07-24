package com.rtm.application.mybatisFlex.component.dbinit;

import com.mybatisflex.core.datasource.DataSourceBuilder;
import com.mybatisflex.core.datasource.FlexDataSource;
import com.mybatisflex.core.dialect.DbType;
import com.mybatisflex.core.dialect.DbTypeUtil;
import com.mybatisflex.spring.boot.MybatisFlexProperties;
import com.rtm.application.mybatisFlex.enums.DataSourcePropKeyEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 *  该组件主要提供应用服务启动时初始化 SQL 执行逻辑
 * @author rtm
 */
@Slf4j
public class SqlInitManager {

    /**
     *  多数据源配置属性
     */
    private final MybatisFlexProperties mybatisFlexProperties;

    /**
     *  应用多数据源
     */
    private FlexDataSource flexDataSource;

    /**
     *  初始化 sql 语句接口，负责提供需要执行初始化的 sql
     */
    private final List<SqlInitStatement> sqlInitStatements;

    /**
     *  初始化执行 SQL 错误处理器
     */
    private final SqlExecuteErrorHandler sqlExecuteErrorHandler;


    public SqlInitManager(MybatisFlexProperties mybatisFlexProperties, List<SqlInitStatement> sqlInitStatements,
                          SqlExecuteErrorHandler sqlExecuteErrorHandler) {
        this.mybatisFlexProperties = mybatisFlexProperties;
        this.sqlInitStatements = sqlInitStatements;
        this.sqlExecuteErrorHandler = sqlExecuteErrorHandler;
        this.startInit();
    }


    /**
     *  获取选择使用的数据库，第一个为默认数据源，默认读取配置文件
     * @return 返回应用需要使用的数据库，默认第一个元素为默认数据源
     */
    public List<String> getSelectedDatabase() {
        return Collections.emptyList();
    }


    /**
     *  初始化
     */
    public void startInit() {
        this.initSql();
        this.initMultiDatasource();
    }


    /**
     *  执行初始化 sql
     */
    public void initSql() {

        beforeInitSql();

        initSql(this::initConsumer);

        afterInitSql();
    }


    /**
     *  初始化 sql 执行前执行
     */
    protected void beforeInitSql() {
        log.error("开始执行数据库初始化 SQL!");
    }


    /**
     *  执行初始化 sql 语句
     * @param initConsumer 执行初始化函数
     */
    private void initSql(Consumer<Map<String,String>> initConsumer) {
        this.mybatisFlexProperties.getDatasource()
                .values()
                .forEach(initConsumer);
    }


    /**
     *  开始执行初始化 sql
     * @param datasourceProperties 数据源信息
     */
    private void initConsumer(Map<String,String> datasourceProperties) {

        if (CollectionUtils.isEmpty(sqlInitStatements)) {
            return;
        }
        if (MapUtils.isEmpty(datasourceProperties)) {
            log.error("未配置数据源信息，请检查数据源信息配置！");
            return;
        }
        Map<DbType, List<SqlInitStatement>> sqlInitStatementMap = sqlInitStatements.stream()
                .collect(Collectors.groupingBy(SqlInitStatement::getDbType));

        if (MapUtils.isEmpty(sqlInitStatementMap)) {
            log.error("未找到初始化数据库 SQL 脚本，跳过初始化！");
            return;
        }

        String url = extractBaseUrl(datasourceProperties.get("url"));
        DbType dbType = DbTypeUtil.parseDbType(url);
        List<SqlInitStatement> sqlInitStmts = sqlInitStatementMap.get(dbType);
        if (CollectionUtils.isEmpty(sqlInitStmts)) {
            log.error("数据库：{} 未找到对应初始化 SQL 语句!", dbType);
            return;
        }

        sqlInitStmts.stream()
                .sorted(Comparator.comparingInt(SqlInitStatement::getOrder))
                .forEach(sqlInitStatement -> executeSqlStatements(sqlInitStatement, dbType, datasourceProperties));
    }


    /**
     * 执行 sql语句校验处理
     * @param sqlInitStatement 需要执行的 sql 语句
     * @param dbType 数据库类型
     * @param datasourceProperties 数据源信息
     */
    private void executeSqlStatements(SqlInitStatement sqlInitStatement, DbType dbType, Map<String, String> datasourceProperties) {
        if (sqlInitStatement == null || !sqlInitStatement.enable()) {
            log.error("数据库：{} 未开启执行初始化 SQL 脚本，跳过执行初始化！", dbType);
            return;
        }

        if (sqlInitStatement.hasInit() && !sqlInitStatement.repeatExecute()) {
            log.error("数据库：{} 已经执行过初始化 SQL,未开启重复执行，跳过初始化!", dbType);
            return;
        }

        List<String> sqlStatements = sqlInitStatement.getSql();
        if (CollectionUtils.isEmpty(sqlStatements)) {
            log.error("数据库：{} 初始化 SQL 脚本为空，跳过执行初始化！", dbType);
            return;
        }

        this.loadJdbcDriverIfNecessary(dbType);
        this.executeSqlList(sqlStatements, dbType, datasourceProperties, sqlInitStatement);
    }


    /**
     *  主动加载 JDBC 驱动
     * @param dbType 数据库类型
     */
    private void loadJdbcDriverIfNecessary(DbType dbType) {
        if (DbType.GBASE_8S.equals(dbType)) {
            try {
                Class.forName("com.gbasedbt.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                log.error("数据库：{} 加载 JDBC 驱动出错！", dbType);
                throw new RuntimeException(e);
            }
        }
    }


    /**
     *  执行初始化 SQL
     * @param sqlStatements 需要执行的初始化 SQL
     * @param dbType 数据库类型
     * @param datasourceProperties 数据源信息
     * @param sqlInitStatement  要执行初始化回调的对象
     */
    public void executeSqlList(List<String> sqlStatements, DbType dbType,
               Map<String, String> datasourceProperties, SqlInitStatement sqlInitStatement) {

        String url = extractBaseUrl(datasourceProperties.get("url"));
        String username = datasourceProperties.get("username");
        String password = datasourceProperties.get("password");
        boolean executeSuccess = false;

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            for (String sql : sqlStatements) {
                try {
                    statement.execute(sql);
                } catch (SQLException e) {
                    sqlExecuteErrorHandler.reportError(dbType, sql, e);
                    if (!sqlExecuteErrorHandler.errorContinue()) {
                        throw new RuntimeException(e);
                    }
                }
            }
            executeSuccess = true;
        } catch (SQLException e) {
            log.error("获取数据库： {} 连接或执行初始化 SQL 出错！", dbType, e);
            throw new RuntimeException(e);
        } finally {
            sqlInitStatement.completeInit(executeSuccess);
        }
    }


    /**
     *  提取数据库协议
     * @param dbUrl 数据库地址
     * @return 返回数据库协议信息
     */
    public static String extractBaseUrl(String dbUrl) {
        String url = "";
        try {
            URI uri = new URI(dbUrl.substring(5));
            String protocol = uri.getScheme();
            String host = uri.getHost();
            int port = uri.getPort();
            url = "jdbc:"+protocol + "://" + host + ":" + port;
            if (protocol.contains("gbasedbt")) {
                url = url + "/sys";
            }
        } catch (URISyntaxException e) {
            log.error("配置的 jdbc url 语法错误！", e);
        }
        return url;
    }


    /**
     *  初始化 sql 执行完成后执行
     */
    protected void afterInitSql() {
        log.error("完成执行数据库初始化 SQL!");
    }


    /**
     *  初始化构建多数据源，指定默认的数据源，如果要支持分布式 seta
     */
    public void initMultiDatasource() {

        Map<String, Map<String, String>> datasource = this.selectDataSource();

        FlexDataSource flexDataSource = null;
        for (Map.Entry<String, Map<String, String>> entry : datasource.entrySet()) {
            DataSource dataSource = new DataSourceBuilder(entry.getValue()).build();
            if (flexDataSource == null) {
                flexDataSource = new FlexDataSource(entry.getKey(), dataSource, false);
            } else {
                flexDataSource.addDataSource(entry.getKey(), dataSource, false);
            }
        }
        this.flexDataSource = flexDataSource;
    }


    /**
     *  选择应用使用的数据源
     * @return 返回当前应用需要使用的数据库
     */
    public Map<String, Map<String, String>> selectDataSource() {
        Map<String, Map<String, String>> datasource = this.mybatisFlexProperties.getDatasource();
        List<String> selectedDatabase = this.getSelectedDatabase();
        if (CollectionUtils.isEmpty(selectedDatabase)) {
            Object defaultSourceValue = this.mybatisFlexProperties.getConfigurationProperties().get(DataSourcePropKeyEnum.DEFAULT_DB.getName());
            String defaultDataSourceKey = defaultSourceValue != null ? defaultSourceValue.toString() : DataSourcePropKeyEnum.DEFAULT_DB.getName();
            selectedDatabase = Arrays.asList(StringUtils.split(defaultDataSourceKey, ","));
        }
        Map<String, Map<String, String>> finalDataSource = new LinkedHashMap<>();
        for (String select : selectedDatabase) {
            if (datasource.containsKey(select)) {
                finalDataSource.put(select, datasource.get(select));
            }
        }
        return finalDataSource;
    }


    /**
     *  获取配置的数据源信息
     */
    public MybatisFlexProperties getMybatisFlexProperties() {
        return this.mybatisFlexProperties;
    }


    /**
     *  获取应用初始化的多数据源信息
     * @return 返回数据源信息
     */
    public FlexDataSource getFlexDataSource() {
        return this.flexDataSource;
    }
}
