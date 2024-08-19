package com.rtm.application.protocol.api.parser.res;

import com.rtm.application.protocol.KafkaProtocolParser;
import com.rtm.application.protocol.message.entity.api.res.DescribeTransactionsResponseBody;
import com.rtm.application.protocol.message.exception.ProtocolParseException;
import java.nio.ByteBuffer;

/**
 * @author rtm
 * @date 2024/08/15
 * DescribeTransactionsResponseParser 协议 payload 解析器.
 */
public class DescribeTransactionsResponseParser implements KafkaProtocolParser<DescribeTransactionsResponseBody> {


    @Override
    public DescribeTransactionsResponseBody parsePacket(ByteBuffer payload) throws ProtocolParseException {
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
        return false;
    }

}
