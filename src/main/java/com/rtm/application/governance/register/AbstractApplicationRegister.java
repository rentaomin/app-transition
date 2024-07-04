package com.rtm.application.governance.register;

import com.rtm.application.governance.EventPublisher;
import javax.annotation.Resource;

public abstract class AbstractApplicationRegister implements ApplicationRegister {

    @Resource
    private EventPublisher eventPublisher;

    public EventPublisher getEventPublisher() {
        return this.eventPublisher;
    }

}
