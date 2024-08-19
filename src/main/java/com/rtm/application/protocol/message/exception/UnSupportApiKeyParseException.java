package com.rtm.application.protocol.message.exception;


/**
 *  不支持解析的 ApiKey 抛出该异常，目前支持的 ApiKey 为 @see {@link com.rtm.application.protocol.message.enums.ApiKeysCode}
 */
public class UnSupportApiKeyParseException extends ProtocolParseException {

    public UnSupportApiKeyParseException(String message) {
        super(message);
    }

    public UnSupportApiKeyParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
