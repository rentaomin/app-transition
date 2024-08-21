package com.rtm.application.protocol.api.parser;

import com.rtm.application.protocol.KafkaProtocolParser;
import com.rtm.application.protocol.message.entity.ResponseHeader;
import com.rtm.application.protocol.message.enums.ApiKeys;
import com.rtm.application.protocol.message.enums.ApiVersion;
import com.rtm.application.protocol.message.exception.ProtocolParseException;
import java.nio.ByteBuffer;

/**
 *  响应头解析器
 * @author rtm
 * @date 2024/08/15
 */
public class DefaultResponseHeaderParser implements KafkaProtocolParser<ResponseHeader> {


    @Override
    public ResponseHeader parsePacket(ByteBuffer payload) throws ProtocolParseException {
        ResponseHeader responseHeader;
        try {
            responseHeader = new ResponseHeader();
            responseHeader.setLength(payload.getInt());
            responseHeader.setCorrelationId(payload.getInt());
            responseHeader.setHeaderLength(4 + 4);
            System.out.println("响应correlationId: "+responseHeader.getCorrelationId());
        } catch (Exception e) {
            throw new ProtocolParseException("响应头协议解析异常！", e);
        }
        return responseHeader;
    }

    @Override
    public short getVersion() {
        return ApiVersion.V1.getVersion();
    }

    @Override
    public short getApiKey() {
        return ApiKeys.RESPONSE_HEADER.getCode();
    }

    @Override
    public boolean isRequestParser() {
        return false;
    }
}
