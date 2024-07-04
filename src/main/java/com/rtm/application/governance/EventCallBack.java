package com.rtm.application.governance;

import com.rtm.application.governance.approve.callback.DefaultAppCallBack;

/**
 *  事件回调函数，若业务执行对应事件需要回调，则实现该接口，若未显示声明回调函数
 *  则采用默认实现 {@linkplain DefaultAppCallBack },
 *  默认仅提供日志记录
 */
@FunctionalInterface
public interface EventCallBack<T extends AbstractApplicationEvent, R> {

    /**
     *  执行事件回调处理逻辑
     * @param event 触发回调的事件信息
     * @param response 该事件执行完成后响应结果数据
     */
    void accept(T event, R response);
}
