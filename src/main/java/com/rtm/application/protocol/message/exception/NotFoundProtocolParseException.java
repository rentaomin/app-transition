package com.rtm.application.protocol.message.exception;

public class NotFoundProtocolParseException extends ProtocolParseException {

    public NotFoundProtocolParseException(String message) {
        super(message);
    }

    public NotFoundProtocolParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
