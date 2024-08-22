package com.rtm.application.protocol.api.parser.req;

import com.rtm.application.protocol.KafkaProtocolParser;
import com.rtm.application.protocol.message.entity.api.RequestTopic;
import com.rtm.application.protocol.message.entity.api.req.MetadataRequestBody;
import com.rtm.application.protocol.message.enums.ApiKeys;
import com.rtm.application.protocol.message.enums.ApiVersion;
import com.rtm.application.protocol.message.exception.ProtocolParseException;
import com.rtm.application.protocol.message.exception.RequestProtocolParseException;
import com.rtm.application.protocol.util.ByteUtils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Metadata Request (Version: 7) => [topics] allow_auto_topic_creation
 *   topics => name
 *     name => STRING
 *   allow_auto_topic_creation => BOOLEAN
 *
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
    public MetadataRequestBody parsePacket(ByteBuffer payload, short version) throws ProtocolParseException {
        if (!supportParse(version)){
            throw new RequestProtocolParseException("不支持解析该版本 "+version+" MetadataRequest 协议");
        }
        if (payload.remaining() < 0) {
            return null;
        }
        MetadataRequestBody metadataRequestBody = new MetadataRequestBody();
        int topicSize = payload.getInt();
        metadataRequestBody.setTopicSize(topicSize);
        List<RequestTopic> topics = new ArrayList<>(topicSize);
        for (int i = 0; i < topicSize; i++) {
            RequestTopic requestTopic = new RequestTopic();
            if (getMinVersion() == version) {
                short topicNameLength = payload.getShort();
                requestTopic.setTopicName(ByteUtils.getString(payload,topicNameLength));
            } else {
                int uuidLength = payload.getInt(16);
                if (uuidLength > 0) {
                    requestTopic.setTopicId(ByteUtils.getString(payload, uuidLength));
                }
                int topicNameLength = ByteUtils.readUnsignedVarint(payload);
                requestTopic.setTopicName(ByteUtils.getString(payload, topicNameLength));
            }
            topics.add(requestTopic);
        }
        metadataRequestBody.setTopics(topics);
        metadataRequestBody.setAllowAutoTopicCreation(payload.get() != 0);
        if (version > getMinVersion()) {
            metadataRequestBody.setIncludeTopicAuthorizedOperations(payload.get() != 0);
        }

        System.out.println("metadataRequestBody 解析结果： \n"+metadataRequestBody);
        return metadataRequestBody;
    }


    @Override
    public short getApiKey() {
        return ApiKeys.Metadata.getCode();
    }



    @Override
    public short getMinVersion() {
        return ApiVersion.V7.getVersion();
    }


    @Override
    public short getMaxVersion() {
        return ApiVersion.V12.getVersion();
    }


    @Override
    public boolean isRequestParser() {
        return true;
    }

}
