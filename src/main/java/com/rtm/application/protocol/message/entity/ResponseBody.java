package com.rtm.application.protocol.message.entity;

/**
 * 标记类，子类继承该接口实现不同的响应消息
 */
public class ResponseBody {

    private byte[] rawData;


    public byte[] getRawData() {
        return rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }
}
