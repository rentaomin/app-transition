package com.rtm.application.protocol.message.exception;

/**
 *  注册解析器出错抛出该异常
 */
public class RegisterProtocolParseException extends ProtocolParseException {

    public RegisterProtocolParseException(String message) {
        super(message);
    }

    public RegisterProtocolParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
