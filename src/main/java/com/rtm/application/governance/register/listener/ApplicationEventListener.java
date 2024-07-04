package com.rtm.application.governance.register.listener;

import com.rtm.application.governance.approve.Approval;
import com.rtm.application.governance.approve.RegisterAuthorize;
import com.rtm.application.governance.register.Application;
import com.rtm.application.governance.register.ApplicationInfo;
import com.rtm.application.governance.register.event.ApplicationRegisteredEvent;
import com.rtm.application.governance.register.event.ApplicationUnRegisteredEvent;
import com.rtm.application.governance.register.event.MultipleApplicationRegisteredEvent;
import com.rtm.application.governance.register.event.MultipleApplicationUnRegisteredEvent;
import com.rtm.application.governance.approve.Authorize;
import com.rtm.application.util.Response;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

/**
 *  发
 */
@Component
public class ApplicationEventListener {

    @Resource
    private RegisterAuthorize authorize;


    @EventListener
    public void onEvent(ApplicationRegisteredEvent event) {
        // 执行审批流程
        Approval approval = authorize.apply(event.getData());
        Response response = Response.builder().success(approval);
        event.getCallBack().accept(event, response);
    }

    @EventListener
    public void onEvent(MultipleApplicationRegisteredEvent event) {
        // 执行审批流程
        List<ApplicationInfo> dataInfo = event.getDataInfo();
        Approval approval = authorize.apply(dataInfo);
        Response response = Response.builder().success(approval);
        event.getCallBack().accept(event, response);
    }

    @EventListener
    public void onEvent(ApplicationUnRegisteredEvent event) {
        // 执行审批流程
        Approval approval = authorize.apply(event.getData());
        Response response = Response.builder().success(approval);
        event.getCallBack().accept(event, response);
    }


    @EventListener
    public void onEvent(MultipleApplicationUnRegisteredEvent event) {
        // 执行审批流程
        Approval approval = authorize.apply(event.getDataInfo());
        Response response = Response.builder().success(approval);
        event.getCallBack().accept(event, response);
    }


}
