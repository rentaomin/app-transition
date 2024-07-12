package com.rtm.application.mybatisFlex.configure;

import com.mybatisflex.core.datasource.FlexDataSource;
import com.mybatisflex.spring.boot.MybatisFlexProperties;
import com.rtm.application.mybatisFlex.component.dbinit.DataSourceProperties;
import com.rtm.application.mybatisFlex.component.dbinit.SqlExecuteErrorHandler;
import com.rtm.application.mybatisFlex.component.dbinit.SqlInitManager;
import com.rtm.application.mybatisFlex.component.dbinit.SqlInitStatement;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Collections;
import java.util.List;

/**
 *  用于多数据源下数据源相关配置
 */
@Configuration
public class DatasourceConfigure {


    /**
     *  应要启动初始化 SQL 错误处理器DataSourcePropKeyEnum
     * @return 返回默认的错误处理器，默认跳过错误
     */
    @Bean
    @ConditionalOnMissingBean
    public SqlExecuteErrorHandler sqlExecuteErrorHandler() {
        return () -> Boolean.TRUE;
    }


    /**
     *  默认 SQL 初始化空实现，空实现则不进行初始化，业务端需要则实现接口接口
     * @return 返回空实现，不进行接口初始化
     */
    @Bean
    @ConditionalOnMissingBean
    public List<SqlInitStatement> sqlInitStatements() {
        return Collections.emptyList();
    }


    /**
     *  SQL 初始化执行 bean
     * @param sqlInitStatements 负责初始化 SQL 语句获取
     * @param sqlExecuteErrorHandler 负责初始化 SQL 错误处理
     * @return 返回 SQL 初始化管理器
     */
    @Bean
    public SqlInitManager sqlInitManager(MybatisFlexProperties mybatisFlexProperties,
               List<SqlInitStatement> sqlInitStatements, SqlExecuteErrorHandler sqlExecuteErrorHandler) {
        return new SqlInitManager(mybatisFlexProperties, sqlInitStatements, sqlExecuteErrorHandler);
    }


    /**
     *  构建多数据源配置基础信息
     * @return 返回数据源基础属性信息
     */
    @Bean
    public DataSourceProperties dataSourceProperties(MybatisFlexProperties mybatisFlexProperties) {
        return new DataSourceProperties(mybatisFlexProperties.getConfigurationProperties(),mybatisFlexProperties.getDatasource());
    }


    /**
     *  多数据源配置下，指定默认数据源，该配置下，不支持动态切换数据源
     * @return 返回默认的数据源信息
     */
    @Bean
    public FlexDataSource flexDataSource(SqlInitManager sqlInitManager) {
        return sqlInitManager.getFlexDataSource();
    }
}
