package com.rtm.application.governance.policy.exchange;

import com.rtm.application.governance.policy.Policy;

/**
 *  该接口为交换策略基础信息顶层接口
 */
public interface ExchangePolicy extends Policy {

    /**
     *  获取数据流转级别
     * @return
     */
    int getDataLevel();

    /**
     *  获取交换策略的请求方向
     * @return
     */
    int getDirection();

}
