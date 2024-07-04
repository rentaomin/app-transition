package com.rtm.application.governance.register;

/**
 *  应用状态枚举类
 */
public enum ApplicationState {

    UN_REGISTER(0,"未注册"),

    APPLYING(1, "申请中"),

    APPROVING(2, "审批中"),

    APPROVED(3,"审批通过"),

    REJECTED(4,"审批拒绝")
    ;

    private Integer code;

    private String desc;

    ApplicationState(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }
}
