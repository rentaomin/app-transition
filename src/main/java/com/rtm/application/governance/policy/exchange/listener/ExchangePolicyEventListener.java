package com.rtm.application.governance.policy.exchange.listener;

import com.rtm.application.governance.policy.exchange.event.ApplicationExchangePolicyEvent;
import com.rtm.application.governance.policy.exchange.event.MultipleApplicationExchangePolicyEvent;
import com.rtm.application.governance.policy.exchange.ExchangePolicyPusher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;


@Component
public class ExchangePolicyEventListener{

    @Resource
    private ExchangePolicyPusher policyPusher;


    @EventListener
    public void onEvent(ApplicationExchangePolicyEvent event) {
        policyPusher.push(event.getData());
    }

    @EventListener
    public void onEvent(MultipleApplicationExchangePolicyEvent event) {
        policyPusher.push(event.getDataInfo());
    }


}
