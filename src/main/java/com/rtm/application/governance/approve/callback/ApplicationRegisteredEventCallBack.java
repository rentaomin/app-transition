package com.rtm.application.governance.approve.callback;

import com.rtm.application.governance.register.event.ApplicationRegisteredEvent;
import com.rtm.application.governance.EventCallBack;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationRegisteredEventCallBack implements EventCallBack<ApplicationRegisteredEvent,Boolean> {

    @Override
    public void accept(ApplicationRegisteredEvent event, Boolean response) {
    }
}
