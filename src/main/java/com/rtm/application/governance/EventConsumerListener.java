package com.rtm.application.governance;

import org.springframework.context.ApplicationListener;

/**
 *  事件消费者监听器，主要用于监听应用发布的事件,业务应用可以实现该接口监听指定的事件
 *   或者通过 {@linkplain org.springframework.context.event.EventListener} 注解监听事件
 *   进行业务处理
 */
@FunctionalInterface
public interface EventConsumerListener<E extends AbstractApplicationEvent> extends ApplicationListener<E> {

}
