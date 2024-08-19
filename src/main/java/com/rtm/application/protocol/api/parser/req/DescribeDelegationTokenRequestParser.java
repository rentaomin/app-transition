package com.rtm.application.protocol.api.parser.req;

import com.rtm.application.protocol.KafkaProtocolParser;
import com.rtm.application.protocol.message.entity.api.req.DescribeDelegationTokenRequestBody;
import com.rtm.application.protocol.message.exception.ProtocolParseException;
import java.nio.ByteBuffer;

/**
 * @author rtm
 * @date 2024/08/15
 * DescribeDelegationTokenRequestParser 协议 payload 解析器.
 */
public class DescribeDelegationTokenRequestParser implements KafkaProtocolParser<DescribeDelegationTokenRequestBody> {


    @Override
    public DescribeDelegationTokenRequestBody parsePacket(ByteBuffer payload) throws ProtocolParseException {
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
