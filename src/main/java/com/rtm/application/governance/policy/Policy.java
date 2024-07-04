package com.rtm.application.governance.policy;

import com.rtm.application.governance.register.Application;

/**
 *  策略基础信息接口
 */
public interface Policy {

    /**
     *  获取策略标识
     * @return
     */
    String getId();

    /**
     *  获取策略名称
     * @return
     */
    String getName();

    /**
     *  获取源端应用信息
     * @return
     */
    Application getSourceApplication();

    /**
     *  获取目标应用
     * @return
     */
    Application getTargetApplication();

    /**
     *  判断当前策略是否开启
     * @return
     */
    Boolean enable();

    /**
     *  获取策略配置时间
     * @return
     */
    long getCreateTime();
}
