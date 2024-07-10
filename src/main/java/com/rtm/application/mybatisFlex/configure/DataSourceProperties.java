package com.rtm.application.mybatisFlex.configure;

import com.rtm.application.mybatisFlex.enums.DataSourcePropKeyEnum;
import java.util.Map;
import java.util.Properties;

/**
 *  该类主要为动态获取应用配置文件中 mybatis-flex: 开头的属性信息
 *  <ul>
 *      <li>获取应用配置文件中开头为 mybatis-flex:configurationProperties: 默认属性基础信息</li>
 *      <li>获取应用配置文件中开头为 mybatis-flex:datasource： 下配置的基础信息</li>
 *      <li>提供基础的获取数据源和默认基础属性方法</li>
 *  </ul>

 */
public class DataSourceProperties {

    /**
     *  数据源默认配置属性信息
     */
    private Properties configurationProperties;

    /**
     *  多数据源基础信息
     */
    private Map<String, Map<String, String>> datasource;

    public DataSourceProperties(Properties configurationProperties, Map<String, Map<String, String>> datasource) {
        this.configurationProperties = configurationProperties;
        this.datasource = datasource;
    }


    /**
     *  获取配置文件 mybatis-flex:configurationProperties: 配置的默认属性基础信息
     * @return 返回配置的基础信息
     */
    public Properties getConfigurationProperties() {
        return configurationProperties;
    }


    /**
     *  根据属性名称获取配置文件 mybatis-flex:configurationProperties: 中配置的属性值
     * @return 返回配置的基础信息
     */
    public String getConfigurationPropertiesValue(DataSourcePropKeyEnum key) {
        return this.configurationProperties.getProperty(key.getName());
    }


    /**
     *  获取配置文件 mybatis-flex: 配置的多数据源属性信息
     * @return 返回配置的多数据源属性信息
     */
    public Map<String, Map<String, String>> getDatasource() {
        return datasource;
    }


    /**
     *  根据指定数据源类型获取配置文件 mybatis-flex: 配置的数据源信息
     * @param dataSourceType 数据源类型
     * @return 返回对应的数据源基础属性信息
     */
    public Map<String, String> getDatasource(String dataSourceType) {
        return this.datasource.get(dataSourceType);
    }

    /**
     *  获取指定数据源中指定属性名称的值
     * @param dataSourceType 数据源类型名称
     * @param dataSourcePropertyKey 属性名称，该值为{@linkplain DataSourcePropKeyEnum} 定义的属性名称
     * @return 返回对应的属性值
     */
    public String getDatasourcePropValue(String dataSourceType, DataSourcePropKeyEnum dataSourcePropertyKey) {
        return getDatasource(dataSourceType).get(dataSourcePropertyKey.getName());
    }
}
