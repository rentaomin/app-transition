package com.rtm.application.governance.policy.exchange.service;

import com.rtm.application.governance.EventPublisher;
import com.rtm.application.governance.policy.Policy;
import com.rtm.application.governance.policy.PolicyManager;
import com.rtm.application.governance.policy.PolicyPusher;
import com.rtm.application.governance.policy.exchange.ExchangePolicy;
import com.rtm.application.governance.policy.exchange.event.MultipleApplicationExchangePolicyEvent;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultPolicyManager implements PolicyManager<ExchangePolicy> {

    List<Policy> data = new ArrayList<>();

    private EventPublisher publisher;

    private PolicyPusher pusher;


    @Override
    public Boolean add(List<ExchangePolicy> policy) {
        boolean addAll = data.addAll(policy);
        publisher.publishEvent(new MultipleApplicationExchangePolicyEvent(this,policy,((event, response) -> {
            pusher.push(event.getDataInfo());
        })));
        return addAll;
    }

    @Override
    public Boolean update(List<ExchangePolicy> policy) {
        return null;
    }

    @Override
    public Boolean delete(List<String> policyIds) {
        return null;
    }

    @Override
    public ExchangePolicy getPolicyById(String id) {
        return null;
    }

    @Override
    public List<ExchangePolicy> getPolicies() {
        return null;
    }
}
