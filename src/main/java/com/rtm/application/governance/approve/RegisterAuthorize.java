package com.rtm.application.governance.approve;

import com.rtm.application.governance.register.ApplicationInfo;
import com.rtm.application.governance.register.Authorize;
import java.util.List;

/**
 *  负责应用注册审批流程处理,提供审批流程业务扩展接口,代办、已办
 */
public interface RegisterAuthorize extends Authorize<ApplicationInfo> {

    /**
     *  待审批应用审批
     * @param approval 包含审批意见信息
     * @return
     */
    boolean approve(Approval approval);

    /**
     *  获取待审批的应用信息
     * @return
     */
    List<ApplicationInfo> getUnApprovedApplicationInfo();

    /**
     *  获取应用的审批信息
     * @param applicationId
     * @return
     */
    Approval getApprovalInfoBy(String applicationId);

    /**
     *  批量获取应用的审批信息
     * @param applicationIds
     * @return
     */
    List<Approval> getApprovalInfoBy(List<String> applicationIds);

}
