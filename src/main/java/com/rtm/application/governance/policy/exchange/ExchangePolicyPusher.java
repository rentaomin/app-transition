package com.rtm.application.governance.policy.exchange;

import com.rtm.application.governance.policy.PolicyPusher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
public class ExchangePolicyPusher implements PolicyPusher<ExchangePolicy> {

    @Override
    public void push(List<ExchangePolicy> policy) {

    }
}
