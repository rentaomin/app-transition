package com.rtm.application.protocol.api.parser.req;

import com.rtm.application.protocol.KafkaProtocolParser;
import com.rtm.application.protocol.message.entity.api.req.DescribeAclsRequestBody;
import com.rtm.application.protocol.message.exception.ProtocolParseException;
import java.nio.ByteBuffer;

/**
 * @author rtm
 * @date 2024/08/15
 * DescribeAclsRequestParser 协议 payload 解析器.
 */
public class DescribeAclsRequestParser implements KafkaProtocolParser<DescribeAclsRequestBody> {


    @Override
    public DescribeAclsRequestBody parsePacket(ByteBuffer payload) throws ProtocolParseException {
        return null;
    }

    @Override
    public short getVersion() {
        return 0;
    }

    @Override
    public short getApiKey() {
        return 0;
    }

    @Override
    public boolean isRequestParser() {
        return true;
    }

}
