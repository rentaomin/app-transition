package com.rtm.application.protocol.message.exception;

public class ResponseProtocolParseException extends ProtocolParseException {

    public ResponseProtocolParseException(String message) {
        super(message);
    }

    public ResponseProtocolParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
