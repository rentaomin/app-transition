package com.rtm.application.governance.approve;

import java.util.List;

/**
 *  负责审批流程处理，该接口为审批业务顶层接口，提供了审批处理、代办查询、已办查询接口，
 *  若需要其他业务子类扩展该接口
 */
public interface Authorize <T> {

    /**
     *  审批处理
     * @param approval 包含审批意见信息和审批关联的主体信息
     * @return
     */
    Boolean approve(T approval);

    /**
     *  获取待审批的信息
     * @return
     */
    List<T> getUnApprovedInfo();

    /**
     *  获取已审批的信息
     * @return
     */
    List<T> getApprovedInfo();

}
