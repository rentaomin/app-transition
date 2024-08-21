package com.rtm.application.protocol.api.parser.req;

import com.rtm.application.protocol.KafkaProtocolParser;
import com.rtm.application.protocol.message.entity.api.RequestTopic;
import com.rtm.application.protocol.message.entity.api.req.MetadataRequestBody;
import com.rtm.application.protocol.message.enums.ApiKeys;
import com.rtm.application.protocol.message.enums.ApiVersion;
import com.rtm.application.protocol.message.exception.ProtocolParseException;
import com.rtm.application.protocol.util.ByteUtils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Metadata Request (Version: 12) => [topics] allow_auto_topic_creation include_topic_authorized_operations TAG_BUFFER
 *   topics => topic_id name TAG_BUFFER
 *     topic_id => UUID
 *     name => COMPACT_NULLABLE_STRING
 *   allow_auto_topic_creation => BOOLEAN
 *   include_topic_authorized_operations => BOOLEAN
 * @author rtm
 * @date 2024/08/15
 * MetadataRequestParser 协议 payload 解析器.
 */
public class MetadataRequestParser implements KafkaProtocolParser<MetadataRequestBody> {


    @Override
    public MetadataRequestBody parsePacket(ByteBuffer payload) throws ProtocolParseException {
        if (payload.remaining() > 0) {
            MetadataRequestBody metadataRequestBody = new MetadataRequestBody();
            int topicSize = payload.getInt();
            metadataRequestBody.setTopicSize(topicSize);
            List<RequestTopic> topics = new ArrayList<>(topicSize);
            for (int i = 0; i < topicSize; i++) {
                RequestTopic requestTopic = new RequestTopic();
                short topicNameLength = payload.getShort();
                requestTopic.setTopicName(ByteUtils.getString(payload,topicNameLength));

//              v12 版本
//                int uuidLength = payload.getInt(16);
//                requestTopic.setTopicId(ByteUtils.getString(payload, uuidLength));
//                int topicNameLength = ByteUtils.readUnsignedVarint(payload);
//                requestTopic.setTopicName(ByteUtils.getString(payload, topicNameLength));
                topics.add(requestTopic);
            }
            metadataRequestBody.setTopics(topics);
//            metadataRequestBody.setAllowAutoTopicCreation(payload.get() != 0);
//            metadataRequestBody.setIncludeTopicAuthorizedOperations(payload.get() != 0);
            System.out.println("metadataRequestBody 解析结果： \n"+metadataRequestBody.toString());
            return metadataRequestBody;
        }
        return null;
    }

    @Override
    public short getVersion() {
        return ApiVersion.V12.getVersion();
    }

    @Override
    public short getApiKey() {
        return ApiKeys.Metadata.getCode();
    }

    @Override
    public boolean isRequestParser() {
        return true;
    }

}
