package com.rtm.application.governance.register.event;

import com.rtm.application.governance.AbstractApplicationEvent;
import com.rtm.application.governance.EventCallBack;
import com.rtm.application.governance.approve.callback.DefaultAppCallBack;
import com.rtm.application.governance.register.ApplicationInfo;
import java.util.List;

/**
 *  注册多个应用事件信息
 */
public class MultipleApplicationRegisteredEvent extends AbstractApplicationEvent<ApplicationInfo> {

    public MultipleApplicationRegisteredEvent(Object source, List<ApplicationInfo> applications) {
        this(source, applications,new DefaultAppCallBack());
    }

    public MultipleApplicationRegisteredEvent(Object source, List<ApplicationInfo> applications , EventCallBack callBack) {
        super(source, applications, callBack);
    }


}
