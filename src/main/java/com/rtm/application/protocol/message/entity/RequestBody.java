package com.rtm.application.protocol.message.entity;

import com.rtm.application.protocol.message.entity.api.Field;

import java.util.List;

/**
 *  标记类，子类继承该接口实现不同的响应消息
 */
public class RequestBody {

    private List<Field> taggedFields;

    public List<Field> getTaggedFields() {
        return taggedFields;
    }

    public void setTaggedFields(List<Field> taggedFields) {
        this.taggedFields = taggedFields;
    }

    @Override
    public String toString() {
        return "RequestBody{" +
                "taggedFields=" + taggedFields +
                '}';
    }
}
