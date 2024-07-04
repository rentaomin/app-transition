package com.rtm.application.governance.policy.exchange.event;

import com.rtm.application.governance.AbstractApplicationEvent;
import com.rtm.application.governance.policy.exchange.ExchangePolicy;
import java.util.Collections;

public class ApplicationExchangePolicyEvent extends AbstractApplicationEvent<ExchangePolicy> {

    public ApplicationExchangePolicyEvent(Object source, ExchangePolicy data) {
        super(source, Collections.singletonList(data));
    }
}
