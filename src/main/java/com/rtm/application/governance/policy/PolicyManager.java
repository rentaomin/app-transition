package com.rtm.application.governance.policy;


import java.util.Collections;
import java.util.List;

/**
 *  策略管理者顶层接口，主要负责策略的配置、下发、查询
 */
public interface PolicyManager<T extends Policy> {

    default Boolean add(T policy) {
        return add(Collections.singletonList(policy));
    }

    Boolean add(List<T> policy);

    default Boolean update(T policy) {
        return update(Collections.singletonList(policy));
    }

    Boolean update(List<T> policy);

    default Boolean delete(String policyId) {
        return delete(Collections.singletonList(policyId));
    }

    Boolean delete(List<String> policyIds);

    T getPolicyById(String id);

    List<T> getPolicies();

}
