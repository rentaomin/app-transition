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
 *
 * Request Header v2 => request_api_key request_api_version correlation_id client_id TAG_BUFFER
 *   request_api_key => INT16
 *   request_api_version => INT16
 *   correlation_id => INT32
 *   client_id => NULLABLE_STRING
 * 请求头解析器
 * @author rtm
 * @date 2023/08/08
 */
public class RequestHeaderParser implements KafkaProtocolParser<RequestHeader> {


    /**
     *  存储读取的数据包长度
     */
    private AtomicInteger readPacketLength = new AtomicInteger(0);

    /**
     * 存储请求包中 correlationId 对应的请求包信息，用于获取对应的响应包
     */
    private Map<Integer,?> correlationIds = new ConcurrentHashMap<>();

    @Override
    public RequestHeader parsePacket(ByteBuffer payload, short version) throws RequestProtocolParseException, UnSupportApiKeyParseException {
        if (!supportParse(getApiKey(), version)) {
            throw new UnSupportApiKeyParseException("暂时不支持  ："+version+"{} Api key 解析！");
        }
        return parseHeader(payload, version);
    }


    /**
     * 解析请求头数据包
     * @param buffer 数据包
     * @param version   版本号
     * @return 返回解析后的请求头数据内容
     * @throws UnSupportApiKeyParseException 不支持的 ApiKey 解析异常
     */
    private RequestHeader parseHeader(ByteBuffer buffer, short version) throws UnSupportApiKeyParseException {
        RequestHeader requestHeader = new RequestHeader();

        int payloadLength = 4;
        int readLength = 0 + payloadLength;
        requestHeader.setLength(getPacketLength(buffer));

        short apiKey = buffer.getShort();
        readLength += 2;
        requestHeader.setApiKey(apiKey);

        short apiVersion = buffer.getShort();
        readLength += 2;
        requestHeader.setApiVersion(apiVersion);

        int correlationId = buffer.getInt();
        System.out.println("correlationId: "+ correlationId);
        readLength += 4;
        requestHeader.setCorrelationId(correlationId);

        if (version > getMinVersion() && version <= getMaxVersion()) {
            short clientIdLength = buffer.getShort();
            readLength += 2;
            if (clientIdLength > 0) {
                String clientId = ByteUtils.getString(buffer,clientIdLength);
                readLength += clientIdLength;
                requestHeader.setClientId(clientId);
            }
        }
        requestHeader.setHeaderLength(readLength);
        return requestHeader;
    }


    @Override
    public short getMinVersion() {
        return ApiVersion.V0.getVersion();
    }

    @Override
    public short getMaxVersion() {
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
