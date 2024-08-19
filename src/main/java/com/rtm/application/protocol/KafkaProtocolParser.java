package com.rtm.application.protocol;

import com.rtm.application.protocol.message.entity.api.Field;
import com.rtm.application.protocol.message.enums.ApiKeysCode;
import com.rtm.application.protocol.message.enums.ApiVersion;
import com.rtm.application.protocol.message.exception.ProtocolParseException;
import com.rtm.application.protocol.message.exception.RequestProtocolParseException;
import com.rtm.application.protocol.message.exception.ResponseProtocolParseException;
import com.rtm.application.protocol.message.exception.UnSupportApiKeyParseException;
import java.nio.ByteBuffer;

/**
 * Kafka 协议解析器
 * @author rtm
 * @param <K> 解析后的协议包内容
 */
public interface KafkaProtocolParser<K> {

    /**
     * 解析协议包内容，解析器只负责解析对应的协议包内容
     * <p>
     * 如：请求头只负责解析请求头，响应头只负责解析响应头，解析器不关心解析内容，解析器只负责解析协议包内容
     * </p>
     * @param payload 协议包内容
     * @return 返回解析后的内容
     * @throws RequestProtocolParseException 解析请求协议包异常，抛出该异常
     * @throws ResponseProtocolParseException 解析响应协议包异常，抛出该异常
     * @throws UnSupportApiKeyParseException 解析请求或响应协议包时，不知此解析异常，抛出该异常
     */
    K parsePacket(ByteBuffer payload) throws ProtocolParseException;

    /**
     *  获取解析器支持的版本，支持解析的版本号 @see {@link ApiVersion}
     * @return 返回版本号，
     */
    short getVersion();

    /**
     *  获取解析器支持的 apiKey,@see {@link ApiKeysCode#getCode()}
     * @return 返回 apiKey 标识
     */
    short getApiKey();

    /**
     * 是否为请求协议解析器还是响应协议解析器
     * @return 返回 true 表示是请求协议解析器，返回 false 表示是响应解析器
     */
    boolean isRequestParser();

    /**
     *  获取协议包长度
     * @param payload 协议包内容
     * @return 返回协议包长度
     */
    default int getPacketLength(ByteBuffer payload) {
        int payloadDataLength = payload.getInt();
        System.out.println(payloadDataLength);
        return payloadDataLength;
    }

    /**
     * 获取 apiKey 和 apiVersion 的组合，标识唯一版本解析器
     * @return 返回 apiKey 和 apiVersion 的组合
     */
    default Field getApiVersionKey() {
        return new Field(String.valueOf(getApiKey()), String.valueOf(getVersion()));
    }
}
