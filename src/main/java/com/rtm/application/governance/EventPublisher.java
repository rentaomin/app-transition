package com.rtm.application.governance;

/**
 *  该接口主要为便于事件发布,统一约束应用事件发布机制，业务应用应全部采用该接口进行发布事件
 */
@FunctionalInterface
public interface EventPublisher extends org.springframework.context.ApplicationEventPublisher {
}
