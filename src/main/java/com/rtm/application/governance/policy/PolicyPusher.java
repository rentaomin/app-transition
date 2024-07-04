package com.rtm.application.governance.policy;


import java.util.Collections;
import java.util.List;

/**
 *  该接口主要负责策略的下发推送
 * @param <T>
 */
public interface PolicyPusher<T extends Policy> {

    /**
     *  策略下发推送
     */
    void push(List<T> policy);

    default void push(T policy) {
        push(Collections.singletonList(policy));
    }
}
