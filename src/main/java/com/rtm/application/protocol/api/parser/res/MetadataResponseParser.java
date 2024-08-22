package com.rtm.application.protocol.api.parser.res;

import com.rtm.application.protocol.KafkaProtocolParser;
import com.rtm.application.protocol.message.entity.api.res.MetadataResponseBody;
import com.rtm.application.protocol.message.enums.ApiKeys;
import com.rtm.application.protocol.message.enums.ApiVersion;
import com.rtm.application.protocol.message.exception.ProtocolParseException;
import com.rtm.application.protocol.message.exception.ResponseProtocolParseException;
import com.rtm.application.protocol.util.ByteUtils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * MetadataResponseParser 协议 payload 解析器.
 *
 * Metadata Response (Version: 7) => throttle_time_ms [brokers] cluster_id controller_id [topics]
 *   throttle_time_ms => INT32
 *   brokers => node_id host port rack
 *     node_id => INT32
 *     host => STRING
 *     port => INT32
 *     rack => NULLABLE_STRING
 *   cluster_id => NULLABLE_STRING
 *   controller_id => INT32
 *   topics => error_code name is_internal [partitions]
 *     error_code => INT16
 *     name => STRING
 *     is_internal => BOOLEAN
 *     partitions => error_code partition_index leader_id leader_epoch [replica_nodes] [isr_nodes] [offline_replicas]
 *       error_code => INT16
 *       partition_index => INT32
 *       leader_id => INT32
 *       leader_epoch => INT32
 *       replica_nodes => INT32
 *       isr_nodes => INT32
 *       offline_replicas => INT32
 *
 * Metadata Response (Version: 12) => throttle_time_ms [brokers] cluster_id controller_id [topics] TAG_BUFFER
 *   throttle_time_ms => INT32
 *   brokers => node_id host port rack TAG_BUFFER
 *     node_id => INT32
 *     host => COMPACT_STRING
 *     port => INT32
 *     rack => COMPACT_NULLABLE_STRING
 *   cluster_id => COMPACT_NULLABLE_STRING
 *   controller_id => INT32
 *   topics => error_code name topic_id is_internal [partitions] topic_authorized_operations TAG_BUFFER
 *     error_code => INT16
 *     name => COMPACT_NULLABLE_STRING
 *     topic_id => UUID
 *     is_internal => BOOLEAN
 *     partitions => error_code partition_index leader_id leader_epoch [replica_nodes] [isr_nodes] [offline_replicas] TAG_BUFFER
 *       error_code => INT16
 *       partition_index => INT32
 *       leader_id => INT32
 *       leader_epoch => INT32
 *       replica_nodes => INT32
 *       isr_nodes => INT32
 *       offline_replicas => INT32
 *     topic_authorized_operations => INT32
 *
 * @author rtm
 * @date 2024/08/15
 */
public class MetadataResponseParser implements KafkaProtocolParser<MetadataResponseBody> {


    @Override
    public MetadataResponseBody parsePacket(ByteBuffer payload, short version) throws ProtocolParseException {
        if (!supportParse(getApiKey(), version)) {
            throw new ResponseProtocolParseException("Unsupported ApiKey: " + getApiKey() + " Version: " + version);
        }

        MetadataResponseBody metadataResponseBody;
        try {
            metadataResponseBody = parse(payload, version);
        } catch (Exception e) {
            throw new ResponseProtocolParseException("Parse MetadataResponseBody Failed", e);
        }
        return metadataResponseBody;
    }

    private MetadataResponseBody parse(ByteBuffer payload, short version) {

        MetadataResponseBody metadataResponseBody = new MetadataResponseBody();
        metadataResponseBody.setThrottleTimeMs(payload.getInt());

        int brokerSize = payload.getInt();
        metadataResponseBody.setBrokerSize(brokerSize);

        metadataResponseBody.setBrokers(parseBrokers(payload, brokerSize, version));

        if (version == getMinVersion()) {
            short clusterIdLength = payload.getShort();
            if (clusterIdLength > 0) {
                metadataResponseBody.setClusterId(ByteUtils.getString(payload, clusterIdLength));
            }
        } else {
            int clusterIdLength = ByteUtils.readUnsignedVarint(payload);
            if (clusterIdLength > 0) {
                metadataResponseBody.setClusterId(ByteUtils.getString(payload, clusterIdLength));
            }
        }

        metadataResponseBody.setControllerId(payload.getInt());

        int topicSize = payload.getInt();
        metadataResponseBody.setTopicSize(topicSize);

        metadataResponseBody.setTopics(parseTopics(payload, topicSize, version));

        if (version > getMinVersion()) {
            metadataResponseBody.setTopicAuthorizedOperations(payload.getInt());
        }

        System.out.println("metadataRequestBody 解析结果为： \n"+ metadataResponseBody);
        return metadataResponseBody;
    }


    private List<MetadataResponseBody.Broker> parseBrokers(ByteBuffer payload, int brokerSize, short version) {
        List<MetadataResponseBody.Broker> brokers = new ArrayList<>(brokerSize);
        for (int i = 0; i < brokerSize; i++) {
            brokers.add(parseBroker(payload, version));
        }
        return brokers;
    }


    private MetadataResponseBody.Broker parseBroker(ByteBuffer payload, short version) {
        MetadataResponseBody.Broker broker = new MetadataResponseBody.Broker();
        broker.setNodeId(payload.getInt());
        if (version == getMinVersion()) {
            short hostLength = payload.getShort();
            broker.setHostLength(hostLength);
            broker.setHost(ByteUtils.getString(payload, hostLength));
        } else {
            int hostLength = ByteUtils.readUnsignedVarint(payload);
            broker.setHost(ByteUtils.getString(payload, hostLength));
        }
        broker.setPort(payload.getInt());

        if (version == getMinVersion()) {
            short rackLength = payload.getShort();
            if (rackLength > 0) {
                broker.setRack(ByteUtils.getString(payload, rackLength));
            }
        } else {
            int rackLength = ByteUtils.readUnsignedVarint(payload);
            if (rackLength > 0) {
                broker.setRack(ByteUtils.getString(payload, rackLength));
            }
        }
        return broker;
    }


    private List<MetadataResponseBody.MetadataTopic> parseTopics(ByteBuffer payload, int topicSize, short version) {
        List<MetadataResponseBody.MetadataTopic> topics = new ArrayList<>();
        for (int i = 0; i < topicSize; i++) {
            topics.add(parseTopic(payload, version));
        }
        return topics;
    }


    private MetadataResponseBody.MetadataTopic parseTopic(ByteBuffer payload, short version) {
        MetadataResponseBody.MetadataTopic topic = new MetadataResponseBody.MetadataTopic();
        topic.setErrorCode(payload.getShort());

        if (version == getMinVersion()) {
            short nameLength = payload.getShort();
            if (nameLength > 0) {
                topic.setName(ByteUtils.getString(payload, nameLength));
            }
        } else {
            int nameLength = ByteUtils.readUnsignedVarint(payload);
            if (nameLength > 0) {
                topic.setName(ByteUtils.getString(payload, nameLength));
            }
            // 读取高 64 位（most significant bits）
            long mostSigBits = payload.getLong();

            // 读取低 64 位（least significant bits）
            long leastSigBits = payload.getLong();
            String uuid = new UUID(mostSigBits, leastSigBits).toString();
            topic.setTopicId(uuid);
        }
        topic.setInternal(payload.get() != 0);

        int partitionSize = payload.getInt();
        topic.setPartitionSize(partitionSize);

        topic.setPartitions(parsePartitions(payload, partitionSize, version));
        return topic;
    }

    private List<MetadataResponseBody.MetadataPartition> parsePartitions(ByteBuffer payload, int partitionSize, short version) {
        List<MetadataResponseBody.MetadataPartition> partitions = new ArrayList<>();
        for (int j = 0; j < partitionSize; j++) {
            partitions.add(parsePartition(payload, version));
        }
        return partitions;
    }


    private MetadataResponseBody.MetadataPartition parsePartition(ByteBuffer payload, short version) {
        MetadataResponseBody.MetadataPartition partition = new MetadataResponseBody.MetadataPartition();
        partition.setErrorCode(payload.getShort());
        partition.setPartitionIndex(payload.getInt());
        partition.setLeaderId(payload.getInt());
        partition.setLeaderEpoch(payload.getInt());

        int replicaNodeSize = payload.getInt();
        partition.setReplicaNodeSize(replicaNodeSize);

        partition.setReplicaNodes(parseNodes(payload, replicaNodeSize, version));

        int isrNodeSize = payload.getInt();
        partition.setIsrNodeSize(isrNodeSize);
        partition.setIsrNodes(parseNodes(payload, replicaNodeSize, version));

        int offlineNodeSize = payload.getInt();
        partition.setIsrNodeSize(offlineNodeSize);
        partition.setIsrNodes(parseNodes(payload, replicaNodeSize, version));

        return partition;
    }


    private List<MetadataResponseBody.Node> parseNodes(ByteBuffer payload, int nodesSize, short version) {
        List<MetadataResponseBody.Node> nodes = new ArrayList<>();
        for (int k = 0; k < nodesSize; k++) {
            MetadataResponseBody.Node replicaNode = new MetadataResponseBody.Node();
            replicaNode.setId(payload.getInt());
            nodes.add(replicaNode);
        }
        return nodes;
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
        return false;
    }

}
