package com.rtm.application.configure;

import com.rtm.application.governance.register.ApplicationRegister;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(ApplicationRegister.class)
@EnableConfigurationProperties()
public class AppTransitionAutoConfigure {
}
