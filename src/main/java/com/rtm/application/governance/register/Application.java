package com.rtm.application.governance.register;

import java.util.Properties;

/**
 *  应用基础信息接口，注册的对应业务应用需要实现该接口
 */
public interface Application {

    /**
     * 应用标识
     *
     * @return
     */
    String getId();

    /**
     *  应用名称
     * @return
     */
    String getName();

    /**
     * 应用所在区域
     *
     * @return
     */
    String getRegion();

    /**
     * 应用 ip 地址
     *
     * @return
     */
    String getIp();

    /**
     * 应用端口
     *
     * @return
     */
    int getPort();

    /**
     * 应用是否启用
     * @return
     */
    Boolean enable();

    /**
     * 判断应用注册审批是否通过,即审批状态 {@linkplain #getState()} 等于 {@link ApplicationState#APPROVED}
     * @return 返回 true :则审批通过， 反之 false
     */
    default Boolean approved() {
        return ApplicationState.APPROVED.equals(getState());
    }

    /**
     *  获取当前应用状态,默认设为未注册 {@linkplain ApplicationState#UN_REGISTER}
     * @return 子类应在应用所处不同阶段返回对应的状态
     */
    ApplicationState getState();

    /**
     * 获取应用扩展属性信息
     * @return
     */
    default Properties getProperties() {
        return new Properties();
    };

}

