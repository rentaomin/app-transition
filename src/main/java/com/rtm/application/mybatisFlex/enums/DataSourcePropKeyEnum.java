package com.rtm.application.mybatisFlex.enums;

/**
 *  应用配置文件中 mybatis-flex：下配置的自定义数据源属性名称枚举
 */
public enum DataSourcePropKeyEnum {

    DEFAULT_DB("defaultDb","标记默认数据源")
    ;

    private String name;

    private String desc;

    DataSourcePropKeyEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
