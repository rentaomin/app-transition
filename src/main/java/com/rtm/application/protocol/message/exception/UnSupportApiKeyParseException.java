package com.rtm.application.protocol.message.exception;


import com.rtm.application.protocol.message.enums.ApiKeys;

/**
 *  不支持解析的 ApiKey 抛出该异常，目前支持的 ApiKey 为 @see {@link ApiKeys}
 */
public class UnSupportApiKeyParseException extends ProtocolParseException {

    public UnSupportApiKeyParseException(String message) {
        super(message);
    }

    public UnSupportApiKeyParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
