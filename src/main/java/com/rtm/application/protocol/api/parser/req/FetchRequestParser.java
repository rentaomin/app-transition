package com.rtm.application.protocol.api.parser.req;

import com.rtm.application.protocol.KafkaProtocolParser;
import com.rtm.application.protocol.message.entity.api.RequestPartition;
import com.rtm.application.protocol.message.entity.api.RequestTopic;
import com.rtm.application.protocol.message.entity.api.req.FetchRequestBody;
import com.rtm.application.protocol.message.enums.ApiKeys;
import com.rtm.application.protocol.message.enums.ApiVersion;
import com.rtm.application.protocol.message.exception.ProtocolParseException;
import com.rtm.application.protocol.util.ByteUtils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Fetch Request (Version: 16) => max_wait_ms min_bytes max_bytes isolation_level session_id session_epoch [topics] [forgotten_topics_data] rack_id TAG_BUFFER
 *   max_wait_ms => INT32
 *   min_bytes => INT32
 *   max_bytes => INT32
 *   isolation_level => INT8
 *   session_id => INT32
 *   session_epoch => INT32
 *   topics => topic_id [partitions] TAG_BUFFER
 *     topic_id => UUID
 *     partitions => partition current_leader_epoch fetch_offset last_fetched_epoch log_start_offset partition_max_bytes TAG_BUFFER
 *       partition => INT32
 *       current_leader_epoch => INT32
 *       fetch_offset => INT64
 *       last_fetched_epoch => INT32
 *       log_start_offset => INT64
 *       partition_max_bytes => INT32
 *   forgotten_topics_data => topic_id [partitions] TAG_BUFFER
 *     topic_id => UUID
 *     partitions => INT32
 *   rack_id => COMPACT_STRING
 * @author rtm
 * @date 2024/08/15
 * FetchRequestParser 协议 payload 解析器.
 */
public class FetchRequestParser implements KafkaProtocolParser<FetchRequestBody> {


    @Override
    public FetchRequestBody parsePacket(ByteBuffer payload, short version) throws ProtocolParseException {
        if (payload.remaining() <= 0) {
            return null;
        }
        FetchRequestBody fetchRequestBody = new FetchRequestBody();
        fetchRequestBody.setReplicaId(payload.getInt());
        fetchRequestBody.setMaxWaitTimeMs(payload.getInt());
        fetchRequestBody.setMinBytes(payload.getInt());
        fetchRequestBody.setMaxBytes(payload.getInt());
        fetchRequestBody.setIsolationLevel(payload.get());
        fetchRequestBody.setSessionId(payload.getInt());
        fetchRequestBody.setSessionEpoch(payload.getInt());

        int topicSize = payload.getInt();
        fetchRequestBody.setTopicSize(topicSize);

        List<RequestTopic> topics = this.parseTopic(payload, topicSize);
        fetchRequestBody.setTopics(topics);

        int forgottenTopicSize = payload.getInt();
        fetchRequestBody.setForgottenTopicsDataSize(forgottenTopicSize);

        fetchRequestBody.setForgottenTopicsData(this.parseTopic(payload, forgottenTopicSize));
        System.out.println("解析完成"+fetchRequestBody);
        return fetchRequestBody;
    }


    /**
     *  解析 topic 数据
     * @param payload 数据包数据
     * @param topicSize topic 数量
     * @return 返回解析后的 topic 数据
     */
    private List<RequestTopic> parseTopic(ByteBuffer payload, int topicSize) {
        List<RequestTopic> topics = new ArrayList<>(topicSize);
        for (int i = 0; i < topicSize; i++) {
            RequestTopic topic = new RequestTopic();
            short topicNameLength = payload.getShort();
            String topicName = ByteUtils.getString(payload, topicNameLength);
            topic.setTopicId(topicName);

            int portionSize = payload.getInt();
            topic.setPartitionSize(portionSize);
            List<RequestPartition> partitions = new ArrayList<>(portionSize);
            for (int j = 0; j < portionSize; j++) {
                RequestPartition partition = new RequestPartition();
                partition.setPartition(payload.getInt());
                partition.setCurrentLeaderEpoch(payload.getInt());
                partition.setFetchOffset(payload.getLong());
                partition.setLogStartOffset(payload.getLong());
                partition.setPartitionMaxBytes(payload.getInt());
                partitions.add(partition);
            }
            topic.setPartitions(partitions);
            topics.add(topic);
        }
        return topics;
    }


    @Override
    public short getMinVersion() {
        return ApiVersion.V16.getVersion();
    }


    @Override
    public short getMaxVersion() {
        return ApiVersion.V16.getVersion();
    }


    @Override
    public short getApiKey() {
        return ApiKeys.FETCH.getCode();
    }

    @Override
    public boolean isRequestParser() {
        return true;
    }

}
