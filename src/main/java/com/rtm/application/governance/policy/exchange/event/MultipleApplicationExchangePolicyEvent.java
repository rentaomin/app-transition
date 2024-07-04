package com.rtm.application.governance.policy.exchange.event;

import com.rtm.application.governance.AbstractApplicationEvent;
import com.rtm.application.governance.EventCallBack;
import com.rtm.application.governance.policy.exchange.ExchangePolicy;
import java.util.List;

public class MultipleApplicationExchangePolicyEvent extends AbstractApplicationEvent<ExchangePolicy> {

    public MultipleApplicationExchangePolicyEvent(Object source, List<ExchangePolicy> data) {
        super(source, data);
    }

    public MultipleApplicationExchangePolicyEvent(Object source, List<ExchangePolicy> data, EventCallBack callBack) {
        super(source, data, callBack);
    }
}
