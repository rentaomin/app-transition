package com.rtm.application.governance.register.event;

import com.rtm.application.governance.AbstractApplicationEvent;
import com.rtm.application.governance.register.Application;
import com.rtm.application.governance.EventCallBack;
import com.rtm.application.governance.register.ApplicationInfo;
import com.rtm.application.util.Response;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Collections;

/**
 *  注册单个应用事件
 */
public class ApplicationRegisteredEvent extends AbstractApplicationEvent<Application> {


    public ApplicationRegisteredEvent(Object source, Application application) {
        this(source,application,null);
    }


    public <R> ApplicationRegisteredEvent(Object source, Application application, EventCallBack<AbstractApplicationEvent, R> callBack) {
        super(source, Collections.singletonList(application), callBack);
    }

}
