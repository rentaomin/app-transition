package com.rtm.application.protocol.api.parser;

import com.rtm.application.protocol.KafkaProtocolParser;
import com.rtm.application.protocol.message.entity.RequestHeader;
import com.rtm.application.protocol.message.enums.ApiKeys;
import com.rtm.application.protocol.message.enums.ApiVersion;
import com.rtm.application.protocol.message.exception.RequestProtocolParseException;
import com.rtm.application.protocol.message.exception.UnSupportApiKeyParseException;
import com.rtm.application.protocol.util.ByteUtils;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 请求头解析器
 * @author rtm
 * @date 2023/08/08
 */
public class DefaultRequestHeaderParser implements KafkaProtocolParser<RequestHeader> {


    /**
     *  存储读取的数据包长度
     */
    private AtomicInteger readPacketLength = new AtomicInteger(0);

    /**
     * 存储请求包中 correlationId 对应的请求包信息，用于获取对应的响应包
     */
    private Map<Integer,?> correlationIds = new ConcurrentHashMap<>();

    @Override
    public RequestHeader parsePacket(ByteBuffer payload) throws RequestProtocolParseException, UnSupportApiKeyParseException {
        return parseHeader(payload, getPacketLength(payload));
    }


    /**
     * 解析请求头数据包
     * @param buffer 数据包
     * @param payloadDataLength 数据包长度
     * @return 返回解析后的请求头数据内容
     * @throws UnSupportApiKeyParseException 不支持的 ApiKey 解析异常
     */
    private RequestHeader parseHeader(ByteBuffer buffer, int payloadDataLength) throws UnSupportApiKeyParseException {
        int payloadLength = 4;
        int readLength = 0 + payloadLength;

        short apiKey = buffer.getShort();
        readLength += 2;

        short apiVersion = buffer.getShort();
        readLength += 2;

        if (!supportParse(apiVersion)) {
            throw new UnSupportApiKeyParseException("暂时不支持 ："+apiVersion+"{} Api key 解析！");
        }

        int correlationId = buffer.getInt();
        System.out.println("correlationId: "+ correlationId);
        readLength += 4;

        short clientIdLength = buffer.getShort();
        readLength += 2;

        String clientId = ByteUtils.getString(buffer,clientIdLength);
        readLength += clientIdLength;

        RequestHeader requestHeader = new RequestHeader();
        requestHeader.setLength(payloadDataLength);
        requestHeader.setApiKey(apiKey);
        requestHeader.setApiVersion(apiVersion);
        requestHeader.setCorrelationId(correlationId);
        requestHeader.setClientId(clientId);
        requestHeader.setHeaderLength(readLength);
        return requestHeader;
    }

    public boolean supportParse(short apiVersion) {
        return ApiKeys.contains(apiVersion);
    }

    @Override
    public short getVersion() {
        return ApiVersion.V2.getVersion();
    }

    @Override
    public short getApiKey() {
        return ApiKeys.REQUEST_HEADER.getCode();
    }

    @Override
    public boolean isRequestParser() {
        return true;
    }
}
