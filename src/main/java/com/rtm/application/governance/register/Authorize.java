package com.rtm.application.governance.register;

import com.rtm.application.governance.approve.Approval;
import java.util.Collections;
import java.util.List;

/**
 *  负责审批注册的应用信息
 */
@FunctionalInterface
public interface Authorize<T extends Application> {


    /**
     *  负责应用的认证申请逻辑处理
     * @param applications 应用信息
     * @return
     */
    Approval authentication(List<T> applications);



    /**
     *  单个应用认证申请
     * @param application 应用信息
     * @return
     */
    default Approval authentication(T application) {
        return authentication(Collections.singletonList(application));
    };

}
