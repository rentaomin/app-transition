package com.rtm.application.protocol.message.exception;

public class RequestProtocolParseException extends ProtocolParseException {

    public RequestProtocolParseException(String message) {
        super(message);
    }

    public RequestProtocolParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
