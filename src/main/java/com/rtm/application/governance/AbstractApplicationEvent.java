package com.rtm.application.governance;

import com.rtm.application.governance.approve.callback.DefaultAppCallBack;
import org.springframework.context.PayloadApplicationEvent;
import java.util.List;

/**
 *  应用事件,注册应用事件需要继承该抽象类
 */
public abstract class AbstractApplicationEvent<T> extends PayloadApplicationEvent {

    /**
     *  事件元数据信息
     */
    private List<T> data;

    /**
     *  事件回调函数
     */
    private EventCallBack callBack;

    public AbstractApplicationEvent(Object source, List<T> data) {
        this(source, data, new DefaultAppCallBack());
    }

    public AbstractApplicationEvent(Object source, List<T> data, EventCallBack callBack) {
        this(source, data, null, callBack);
    }

    public AbstractApplicationEvent(Object source, List<T> data, Object payload, EventCallBack callBack) {
        super(source, payload);
        this.data = data;
        this.callBack = callBack;
    }

    public EventCallBack getCallBack() {
        return callBack;
    }

    /**
     *  获取注册的元数据信息
     * @return 返回元数据信息
     */
    public List<T> getDataInfo() {
        return data;
    }

    /**
     *  获取注册的元数据信息
     * @return 返回元数据信息
     */
    public T getData() {
        if (data.size() != 1) {
            throw new IllegalStateException("Expected single data info but found " + data.size());
        }
        return data.get(0);
    }
}
