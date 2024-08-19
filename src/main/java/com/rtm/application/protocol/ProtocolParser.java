package com.rtm.application.protocol;

import com.rtm.application.protocol.message.entity.ProtocolMessage;
import com.rtm.application.protocol.message.exception.ProtocolParseException;

/**
 *  协议解析器，负责解析协议包
 */
@FunctionalInterface
public interface ProtocolParser<T> {

    /**
     *  解析协议包
     * @param data 协议包数据
     * @return 返回解析后的数据内容
     * @throws ProtocolParseException 解析出错抛出该异常
     */
    T parse(ProtocolMessage data) throws ProtocolParseException;

}
