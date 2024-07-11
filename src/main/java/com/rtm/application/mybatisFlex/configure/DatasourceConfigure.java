package com.rtm.application.mybatisFlex.configure;

import com.mybatisflex.core.datasource.DataSourceBuilder;
import com.mybatisflex.core.datasource.FlexDataSource;
import com.mybatisflex.spring.boot.MybatisFlexProperties;
import com.rtm.application.mybatisFlex.component.dbinit.DataSourceProperties;
import com.rtm.application.mybatisFlex.component.dbinit.SqlInitManager;
import com.rtm.application.mybatisFlex.enums.DataSourcePropKeyEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *  用于多数据源下数据源相关配置
 */
@Slf4j
@Configuration
public class DatasourceConfigure {


    @Resource
    private SqlInitManager sqlInitManager;


    /**
     *  多数据源配置下，指定默认数据源，该配置下，不支持动态切换数据源
     * @param flexProperties 动态数据源属性配置
     * @return 返回默认的数据源信息
     */
    @Bean
    public FlexDataSource flexDataSource(MybatisFlexProperties flexProperties) {
        sqlInitManager.initSql(flexProperties);
        return buildMultiDatasource(flexProperties);
    }


    /**
     *  构建多数据源，指定默认的数据源，如果要支持分布式seta
     * @param flexProperties 多数据源属性
     * @return 返回构建的多数据源信息
     */
    private FlexDataSource buildMultiDatasource(MybatisFlexProperties flexProperties) {

        Map<String, Map<String, String>> datasource = this.selectDataSource(flexProperties);

        FlexDataSource flexDataSource = null;
        for (Map.Entry<String, Map<String, String>> entry : datasource.entrySet()) {
            DataSource dataSource = new DataSourceBuilder(entry.getValue()).build();
            if (flexDataSource == null) {
                flexDataSource = new FlexDataSource(entry.getKey(), dataSource, false);
            } else {
                flexDataSource.addDataSource(entry.getKey(), dataSource, false);
            }
        }
        return flexDataSource;
    }


    /**
     *  选择应用使用的数据源
     * @param flexProperties 多数据源配置属性
     * @return 返回当前应用需要使用的数据库
     */
    private Map<String, Map<String, String>> selectDataSource(MybatisFlexProperties flexProperties) {
        Map<String, Map<String, String>> datasource = flexProperties.getDatasource();
        List<String> selectedDatabase = sqlInitManager.getSelectedDatabase();
        if (CollectionUtils.isEmpty(selectedDatabase)) {
            Object defaultSourceValue = flexProperties.getConfigurationProperties().get(DataSourcePropKeyEnum.DEFAULT_DB.getName());
            String defaultDataSourceKey = defaultSourceValue != null ? defaultSourceValue.toString() : DataSourcePropKeyEnum.DEFAULT_DB.getName();
            selectedDatabase = Arrays.asList(StringUtils.split(defaultDataSourceKey, ","));
        }
        datasource.keySet().retainAll(selectedDatabase);
        return datasource;
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
