package com.rtm.application.protocol;

import com.rtm.application.protocol.message.entity.api.ApiVersionInfo;
import com.rtm.application.protocol.message.enums.ApiKeys;
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
     * 解析指定版本号的协议包内容，解析器只负责解析对应的协议包内容,子类需要判断解析器是否支持指定版本号 API 解析
     * <p>
     * 如：请求头只负责解析请求头，响应头只负责解析响应头，解析器不关心解析内容，解析器只负责解析协议包内容
     * </p>
     * @param payload 协议包内容
     * @param version 协议包版本号,版本号应在 [{@link #getMinVersion()},{@link #getMaxVersion()}] 支持的解析范围内
     * @return 返回解析后的内容
     * @throws RequestProtocolParseException 解析请求协议包异常，抛出该异常
     * @throws ResponseProtocolParseException 解析响应协议包异常，抛出该异常
     * @throws UnSupportApiKeyParseException 解析请求或响应协议包时，不支持指定版本解析异常，抛出该异常
     */
    K parsePacket(ByteBuffer payload, short version) throws ProtocolParseException;

    /**
     *  获取解析器支持的最小版本号，解析的API版本号在该版本范围内 @see {@link ApiVersion}
     * @return 返回版本号
     */
    short getMinVersion();

    /**
     *  获取解析器支持的最大版本号，解析的API版本号在该版本范围内 @see {@link ApiVersion}
     * @return 返回版本号
     */
    short getMaxVersion();

    /**
     *  获取解析器支持的 apiKey,@see {@link ApiKeys#getCode()}
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
        return payloadDataLength;
    }

    /**
     * 获取 apiKey 版本信息
     * @return 返回 apiKey 版本信息，包含支持的最小版本号和最大版本号
     */
    default ApiVersionInfo getApiVersionKey() {
        return new ApiVersionInfo(getApiKey(), getMinVersion(), getMaxVersion());
    }


    /**
     *  判断当前 API 版本号是否支持解析
     * @param apiKey 需要解析的 api key
     * @param apiVersion 需要解析的 api 版本号
     * @return 返回 true 表示支持解析，false 表示不支持解析
     */
    default boolean supportParse(short apiKey, short apiVersion) {
        return getApiKey() == apiKey && apiVersion >= getMinVersion() && apiVersion <= getMaxVersion();
    }


    /**
     *  判断 API 解析器是否支持当前版本号解析
     * @param apiVersion 需要解析的 api 版本号
     * @return 返回 true 表示支持解析，false 表示不支持解析
     */
    default boolean supportParse(short apiVersion) {
       return supportParse(getApiKey(), apiVersion);
    }
}
