package com.rtm.application.util.enums;

/**
 * 类功能描述：
 *
 * @author Administrator
 * @date 2023/07/26
 */
public enum CodeEnums {
    SUCCESS(200,"请求成功"),

    FAILURE(1000,"请求失败！"),

    ERROR(500,"服务器错误！"),

    AUTH_FAILURE(401,"没有权限！"),

    AUTH_EXCEPTION(400,"认证出错！"),

    FORBIDDEN(403,"没有权限！"),

    THROWABLE(3000,"未知异常！"),

    EXCEPTION(3200,"程序待检查异常！"),

    RUNTIME_EXCEPTION(3500,"程序运行时异常！"),

    GLOBAL_EXCEPTION(4000,"自定义业务异常！"),

    PARAMS_EMPTY_EXCEPTION(4001,"参数为空异常"),
    ;

    private int code;

    private String desc;

    CodeEnums(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
