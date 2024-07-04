package com.rtm.application.governance.register.service.controller;

import com.rtm.application.governance.AbstractApplicationEvent;
import com.rtm.application.governance.register.ApplicationInfo;
import com.rtm.application.governance.register.event.ApplicationRegisteredEvent;
import com.rtm.application.governance.register.event.ApplicationUnRegisteredEvent;
import com.rtm.application.governance.register.event.MultipleApplicationRegisteredEvent;
import com.rtm.application.governance.register.event.MultipleApplicationUnRegisteredEvent;
import com.rtm.application.governance.register.Application;
import com.rtm.application.governance.EventPublisher;
import com.rtm.application.governance.register.ApplicationRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DefaultApplicationRegister implements ApplicationRegister<ApplicationInfo> {

    @Resource
    private EventPublisher eventPublisher;

    private List<ApplicationInfo> apps = new ArrayList<>(16);


    @Override
    public Boolean register(List<ApplicationInfo> applications) {
        boolean add = apps.addAll(applications);
        eventPublisher.publishEvent(new MultipleApplicationRegisteredEvent(this,applications));
        return false;
    }

    @Override
    public Boolean unRegister(List<ApplicationInfo> applications) {
        apps.removeAll(applications);
        eventPublisher.publishEvent(new MultipleApplicationUnRegisteredEvent(this, applications));
        return false;
    }

    @Override
    public List<ApplicationInfo> getApplication() {
        return this.apps;
    }


    @Override
    public ApplicationInfo getApplicationById(String applicationId) {
        return null;
    }

    @Override
    public List<ApplicationInfo> getApplication(Predicate<ApplicationInfo> filter) {
        return this.apps.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

}
