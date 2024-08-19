package com.rtm.application.protocol.message.entity;


/**
 *  Kafka 消息体
 *
 *  RequestOrResponse => Size (RequestMessage | ResponseMessage)
 *   Size => int32
 */
public class Message {

    /**
     * 	The message_size field gives the size of the subsequent request or response message in bytes.
     * 	The client can read requests by first reading this 4 byte size as an integer N, and then
     * 	reading and parsing the subsequent N bytes of the request.
     * 	Size => int32
     */
    private int messageSize;

    private RequestMessage requestMessage;

    private ResponseMessage responseMessage;

    private boolean parseComplete = false;

    public int getMessageSize() {
        return messageSize;
    }

    public void setMessageSize(int messageSize) {
        this.messageSize = messageSize;
    }

    public RequestMessage getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(RequestMessage requestMessage) {
        this.requestMessage = requestMessage;
    }

    public ResponseMessage getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(ResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }

    public boolean isParseComplete() {
        return parseComplete;
    }

    public void setParseComplete(boolean parseComplete) {
        this.parseComplete = parseComplete;
    }
}
