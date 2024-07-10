package com.rtm.application.mybatisFlex.configure;

import com.mybatisflex.core.datasource.DataSourceBuilder;
import com.mybatisflex.core.datasource.FlexDataSource;
import com.mybatisflex.core.dialect.DbType;
import com.mybatisflex.core.dialect.DbTypeUtil;
import com.mybatisflex.spring.boot.MybatisFlexProperties;
import com.rtm.application.mybatisFlex.enums.DataSourcePropKeyEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;
import javax.sql.DataSource;
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
 *  用于多数据源下数据源相关配置
 */
@Slf4j
@Configuration
public class DatasourceConfigure {


    @Resource
    private List<SqlInitStatement> sqlStatements;

    /**
     *  多数据源配置下，指定默认数据源，该配置下，不支持动态切换数据源
     * @param flexProperties 动态数据源属性配置
     * @return 返回默认的数据源信息
     */
    @Bean
    public FlexDataSource flexDataSource(MybatisFlexProperties flexProperties) {
        initSql(flexProperties,this::doInitSql);
        return buildMultiDatasource(flexProperties);
    }


    /**
     *  执行初始化 sql 语句
     * @param flexProperties 数据源配置信息
     */
    private void initSql(MybatisFlexProperties flexProperties, Consumer<Map<String,String>> initConsumer) {
        flexProperties.getDatasource()
                .values()
                .stream()
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
        String username = datasourceProperties.get("username");
        String password = datasourceProperties.get("password");
        String url = extractBaseUrl(datasourceProperties.get("url"));
        DbType dbType = DbTypeUtil.parseDbType(url);
        if (MapUtils.isEmpty(sqlInitStatementMap)) {
            return;
        }
        SqlInitStatement sqlInitStatement;
        try {
            sqlInitStatement = sqlInitStatementMap.get(dbType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (!sqlInitStatement.enable()) {
            return;
        }

        if (sqlInitStatement.hasInit() && !sqlInitStatement.repeatExecute()) {
            return;
        }

        String sql = sqlInitStatement.getSql();
        if (StringUtils.isBlank(sql)) {
            return;
        }
        if (DbType.GBASE_8S.equals(dbType)) {
            try {
                Class.forName("com.gbasedbt.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        boolean executeSuccess = false;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.execute(sql);
            executeSuccess = true;
            log.error("sql init 执行完成！");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            sqlInitStatement.completeCallback(executeSuccess);
        }
    }


    /**
     *  构建多数据源，指定默认的数据源，如果要支持分布式seta
     * @param flexProperties 多数据源属性
     * @return 返回构建的多数据源信息
     */
    private FlexDataSource buildMultiDatasource(MybatisFlexProperties flexProperties) {
        Map<String, Map<String, String>> datasource = flexProperties.getDatasource();
        Object defaultSourceValue = flexProperties.getConfigurationProperties().get(DataSourcePropKeyEnum.DEFAULT_DB.getName());
        String defaultDataSourceKey = defaultSourceValue != null ? defaultSourceValue.toString() : DataSourcePropKeyEnum.DEFAULT_DB.getName();
        DataSource defaultDataSource = new DataSourceBuilder(datasource.get(defaultDataSourceKey)).build();
        FlexDataSource  flexDataSource = new FlexDataSource(defaultDataSourceKey,defaultDataSource);
        datasource.remove(defaultDataSourceKey);
        for (Map.Entry<String, Map<String, String>> entry : datasource.entrySet()) {
            DataSource dataSource = new DataSourceBuilder(entry.getValue()).build();
            flexDataSource.addDataSource(entry.getKey(), dataSource, false);
        }
        return flexDataSource;
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
     *  构建多数据源配置基础信息
     * @param flexProperties mybatis-flex 配置属性
     * @return 返回数据源基础属性信息
     */
    @Bean
    public DataSourceProperties dataSourceProperties(MybatisFlexProperties flexProperties) {
        return new DataSourceProperties(flexProperties.getConfigurationProperties(),flexProperties.getDatasource());
    }


}
