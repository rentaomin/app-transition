package com.rtm.application.protocol.api.parser;

import com.rtm.application.protocol.KafkaProtocolParser;
import com.rtm.application.protocol.message.entity.ResponseHeader;
import com.rtm.application.protocol.message.enums.ApiKeys;
import com.rtm.application.protocol.message.enums.ApiVersion;
import com.rtm.application.protocol.message.exception.ProtocolParseException;
import com.rtm.application.protocol.message.exception.ResponseProtocolParseException;
import com.rtm.application.protocol.message.exception.UnSupportApiKeyParseException;
import java.nio.ByteBuffer;

/**
 *  响应头解析器
 *
 *  Response Header v1 => correlation_id TAG_BUFFER
 *   correlation_id => INT32
 * @author rtm
 * @date 2024/08/15
 */
public class ResponseHeaderParser implements KafkaProtocolParser<ResponseHeader> {


    @Override
    public ResponseHeader parsePacket(ByteBuffer payload, short version) throws ProtocolParseException {
        if (supportParse(getApiKey(),version)) {
            throw new UnSupportApiKeyParseException("暂时不支持  ："+version+"{} Api key 解析！");
        }
        ResponseHeader responseHeader;
        try {
            responseHeader = new ResponseHeader();
            responseHeader.setLength(payload.getInt());
            responseHeader.setCorrelationId(payload.getInt());
            responseHeader.setHeaderLength(4 + 4);
            System.out.println("响应correlationId: "+responseHeader.getCorrelationId());
        } catch (Exception e) {
            throw new ResponseProtocolParseException("响应头协议解析异常！", e);
        }
        return responseHeader;
    }

    @Override
    public short getMinVersion() {
        return ApiVersion.V0.getVersion();
    }

    @Override
    public short getMaxVersion() {
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
