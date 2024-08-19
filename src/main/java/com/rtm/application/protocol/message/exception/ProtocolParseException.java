package com.rtm.application.protocol.message.exception;

public class ProtocolParseException extends Exception {

    public ProtocolParseException(String message) {
        super(message);
    }

    public ProtocolParseException(String message, Throwable cause) {
        super(message, cause);
    }
}