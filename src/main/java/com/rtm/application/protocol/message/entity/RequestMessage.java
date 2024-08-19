package com.rtm.application.protocol.message.entity;


public class RequestMessage {

    private RequestHeader header;

    private RequestBody payload;

    /**
     *  标记请求包是否解析完成，包含 header 和 payload 全部解析完成
     */
    private boolean parseComplete;

    public RequestHeader getHeader() {
        return header;
    }

    public void setHeader(RequestHeader header) {
        this.header = header;
    }

    public RequestBody getPayload() {
        return payload;
    }

    public void setPayload(RequestBody payload) {
        this.payload = payload;
    }


    public boolean isParseComplete() {
        return parseComplete;
    }

    public void setParseComplete(boolean parseComplete) {
        this.parseComplete = parseComplete;
    }
}
