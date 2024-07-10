package com.rtm.application.mybatisFlex.enums;

/**
 *  数据源属性名称枚举
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
