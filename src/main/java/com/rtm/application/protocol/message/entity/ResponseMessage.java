package com.rtm.application.protocol.message.entity;

/**
 *  响应消息内容
 */
public class ResponseMessage {

    private ResponseHeader header;

    private ResponseBody payload;

    private boolean parseComplete;

    public ResponseHeader getHeader() {
        return header;
    }

    public void setHeader(ResponseHeader header) {
        this.header = header;
    }

    public ResponseBody getPayload() {
        return payload;
    }

    public void setPayload(ResponseBody payload) {
        this.payload = payload;
    }

    public boolean isParseComplete() {
        return parseComplete;
    }

    public void setParseComplete(boolean parseComplete) {
        this.parseComplete = parseComplete;
    }
}
