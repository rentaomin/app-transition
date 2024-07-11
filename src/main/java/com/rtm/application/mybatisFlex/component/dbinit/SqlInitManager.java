package com.rtm.application.mybatisFlex.component.dbinit;

import com.mybatisflex.core.dialect.DbType;
import com.mybatisflex.core.dialect.DbTypeUtil;
import com.mybatisflex.spring.boot.MybatisFlexProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *  该组件主要提供应用服务启动时初始化 SQL 执行逻辑
 */
@Slf4j
@Component
public class SqlInitManager {

    @Resource
    private List<SqlInitStatement> sqlStatements;


    /**
     *  执行初始化 sql
     * @param flexProperties 数据源配置信息
     */
    public void initSql(MybatisFlexProperties flexProperties) {

        before(flexProperties);

        initSql(flexProperties,this::doInitSql);

        after();
    }

    /**
     *  初始化 sql 执行前执行
     * @param flexProperties 数据源配置信息
     */
    protected void before(MybatisFlexProperties flexProperties) {
        log.error("开始执行数据库初始化 SQL!");
    }


    /**
     *  执行初始化 sql 语句
     * @param flexProperties 数据源配置信息
     * @param initConsumer 执行初始化函数
     */
    private void initSql(MybatisFlexProperties flexProperties, Consumer<Map<String,String>> initConsumer) {
        flexProperties.getDatasource()
                .values()
                .forEach(db -> {
                    initConsumer.accept(db);
                });
    }


    /**
     *  开始执行初始化 sql
     * @param datasourceProperties 数据源信息
     */
    private void doInitSql(Map<String,String> datasourceProperties) {

        Map<DbType, SqlInitStatement> sqlInitStatementMap = sqlStatements.stream()
                .collect(Collectors.toMap(SqlInitStatement::getDbType, Function.identity()));

        if (MapUtils.isEmpty(sqlInitStatementMap)) {
            log.error("未找到初始化数据库 SQL 脚本，跳过初始化！");
            return;
        }

        String url = extractBaseUrl(datasourceProperties.get("url"));
        DbType dbType = DbTypeUtil.parseDbType(url);
        SqlInitStatement sqlInitStatement = null;
        try {
            sqlInitStatement = sqlInitStatementMap.get(dbType);
        } catch (Exception e) {
            if (sqlInitStatement == null) {
                log.error("数据库：{} 未找到对应初始化 SQL 语句!", dbType);
            }
            return;
        }
        if (!sqlInitStatement.enable()) {
            log.error("数据库：{} 未开启执行初始化 SQL 脚本，跳过执行初始化！", dbType);
            return;
        }

        if (sqlInitStatement.hasInit() && !sqlInitStatement.repeatExecute()) {
            log.error("数据库：{} 已经执行过初始化 SQL,未开启重复执行，跳过初始化!", dbType);
            return;
        }

        String sql = sqlInitStatement.getSql();
        if (StringUtils.isBlank(sql)) {
            log.error("数据库：{} 初始化 SQL 脚本为空，跳过执行初始化！", dbType);
            return;
        }
        // gbase 驱动存在有时无法注册，进行主动加载
        if (DbType.GBASE_8S.equals(dbType)) {
            try {
                Class.forName("com.gbasedbt.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                log.error("数据库：{} 加载 JDBC 驱动出错！", dbType);
                throw new RuntimeException(e);
            }
        }
        String username = datasourceProperties.get("username");
        String password = datasourceProperties.get("password");
        boolean executeSuccess = false;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.execute(sql);
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
    protected void after() {
        log.error("完成执行数据库初始化 SQL!");
    }

}
