package com.rtm.application.governance.register.event;

import com.rtm.application.governance.AbstractApplicationEvent;
import com.rtm.application.governance.register.Application;
import com.rtm.application.governance.approve.callback.DefaultAppCallBack;
import com.rtm.application.governance.EventCallBack;
import com.rtm.application.governance.register.ApplicationInfo;

import java.util.Collections;


/**
 *  注销单个应用事件
 */
public class ApplicationUnRegisteredEvent extends AbstractApplicationEvent<ApplicationInfo> {

    public ApplicationUnRegisteredEvent(Object source, ApplicationInfo application) {
        this(source,application, new DefaultAppCallBack());
    }


    public <R> ApplicationUnRegisteredEvent(Object source, ApplicationInfo application, EventCallBack<AbstractApplicationEvent, R> callBack) {
        super(source, Collections.singletonList(application), callBack);
    }

}
