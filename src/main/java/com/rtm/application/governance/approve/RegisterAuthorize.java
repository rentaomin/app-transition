package com.rtm.application.governance.approve;

import com.rtm.application.governance.register.Application;
import java.util.Collections;
import java.util.List;

/**
 *  负责应用注册审批流程处理,提供审批流程业务扩展接口,应用注册申请、查询
 *  其中泛型 T 主要为约束审批的注册信息为应用信息
 *
 */
public interface RegisterAuthorize<T extends Application> extends Authorize<Approval> {

    /**
     *  获取待审批的应用信息
     * @return
     */
    List<T> getUnApprovedApplicationInfo();

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

    /**
     *  负责应用的认证申请逻辑处理
     * @param applications 应用信息
     * @return
     */
    Approval apply(List<T> applications);

    /**
     *  单个应用认证申请
     * @param application 应用信息
     * @return
     */
    default Approval apply(T application) {
        return apply(Collections.singletonList(application));
    };

}
