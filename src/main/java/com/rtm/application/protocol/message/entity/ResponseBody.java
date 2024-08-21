package com.rtm.application.protocol.message.entity;

import com.rtm.application.protocol.message.entity.api.Field;
import java.util.List;

/**
 * 标记类，子类继承该接口实现不同的响应消息
 */
public class ResponseBody {

    private List<Field> tagBuffer;

    public List<Field> getTagBuffer() {
        return tagBuffer;
    }

    public void setTagBuffer(List<Field> tagBuffer) {
        this.tagBuffer = tagBuffer;
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "tagBuffer=" + tagBuffer +
                '}';
    }
}
