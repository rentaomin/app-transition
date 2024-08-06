//package com.rtm.application.mybatisFlex.page;
//
//import com.github.pagehelper.PageInterceptor;
//import com.github.pagehelper.autoconfigure.PageHelperProperties;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//import java.util.Properties;
//
//@Configuration
//@ConditionalOnBean(SqlSessionFactory.class)
//@EnableConfigurationProperties(PageHelperProperties.class)
//@AutoConfigureAfter(MybatisAutoConfiguration.class)
//public class PageConfiguration {
//
//
//    @Autowired
//    private List<SqlSessionFactory> sqlSessionFactoryList;
//
//    @Autowired
//    private PageHelperProperties properties;
//
////    @Bean
////    @ConfigurationProperties(prefix = PageHelperProperties.PAGEHELPER_PREFIX)
////    public Properties pageHelperProperties() {
////        Properties properties = new Properties();
////        return properties;
////    }
//    @PostConstruct
//    public void addPageInterceptor(PageHelperProperties pageHelperProperties) {
//        PageInterceptor interceptor = new PageInterceptor();
//        Properties properties = new Properties();
//        //先把一般方式配置的属性放进去
//        properties.putAll(pageHelperProperties.getProperties());
//        //在把特殊配置放进去，由于close-conn 利用上面方式时，属性名就是 close-conn 而不是 closeConn，所以需要额外的一步
//        properties.putAll(this.properties.getProperties());
//        properties.put("dialect","com.rtm.application.mybatisFlex.page.GBaseDialect");
//        interceptor.setProperties(properties);
//        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
//            sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
//        }
//    }
//}
