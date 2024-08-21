package com.rtm.application.protocol.api.parser.res;

import com.rtm.application.protocol.KafkaProtocolParser;
import com.rtm.application.protocol.message.entity.api.res.MetadataResponseBody;
import com.rtm.application.protocol.message.enums.ApiKeys;
import com.rtm.application.protocol.message.enums.ApiVersion;
import com.rtm.application.protocol.message.exception.ProtocolParseException;
import com.rtm.application.protocol.util.ByteUtils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * MetadataResponseParser 协议 payload 解析器.
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
    public MetadataResponseBody parsePacket(ByteBuffer payload) throws ProtocolParseException {

        MetadataResponseBody metadataResponseBody = new MetadataResponseBody();
        metadataResponseBody.setThrottleTimeMs(payload.getInt());
        int brokerSize = payload.getInt();
        metadataResponseBody.setBrokerSize(brokerSize);
        List<MetadataResponseBody.Broker> brokers = new ArrayList<>();
        for (int i = 0; i < brokerSize; i++) {
            MetadataResponseBody.Broker broker = new MetadataResponseBody.Broker();
            broker.setNodeId(payload.getInt());
            short hostLength = payload.getShort();
            broker.setHostLength(hostLength);
            if (hostLength > 0) {
                broker.setHost(ByteUtils.getString(payload, hostLength));
            }
            broker.setPort(payload.getInt());
            short rackLength = payload.getShort();
            if (rackLength > 0) {
                broker.setRack(ByteUtils.getString(payload, rackLength));
            }
            brokers.add(broker);
        }
        metadataResponseBody.setBrokers(brokers);

        short clusterIdLength = payload.getShort();
        if (clusterIdLength > 0) {
            metadataResponseBody.setClusterId(ByteUtils.getString(payload, clusterIdLength));
        }

//        int clusterIdLength = ByteUtils.readUnsignedVarint(payload);
//        if (clusterIdLength > 0) {
//            metadataResponseBody.setClusterId(ByteUtils.getString(payload, clusterIdLength));
//        }
        metadataResponseBody.setControllerId(payload.getInt());
        int topicSize = payload.getInt();
        metadataResponseBody.setTopicSize(topicSize);
        List<MetadataResponseBody.MetadataTopic> topics = new ArrayList<>();
        for (int i = 0; i < topicSize; i++) {
            MetadataResponseBody.MetadataTopic topic = new MetadataResponseBody.MetadataTopic();
            topic.setErrorCode(payload.getShort());
            short nameLength = payload.getShort();
            if (nameLength > 0) {
                topic.setName(ByteUtils.getString(payload, nameLength));
            }
            topic.setInternal(payload.get() != 0);

            int partitionSize = payload.getInt();
            topic.setPartitionSize(partitionSize);
            List<MetadataResponseBody.MetadataPartition> partitions = new ArrayList<>();
            for (int j = 0; j < partitionSize; j++) {
                MetadataResponseBody.MetadataPartition partition = new MetadataResponseBody.MetadataPartition();
                partition.setErrorCode(payload.getShort());
                partition.setPartitionIndex(payload.getInt());
                partition.setLeaderId(payload.getInt());
                partition.setLeaderEpoch(payload.getInt());

                int replicaNodeSize = payload.getInt();
                partition.setReplicaNodeSize(replicaNodeSize);
                List<MetadataResponseBody.Node> replicaNodes = new ArrayList<>();
                for (int k = 0; k < replicaNodeSize; k++) {
                    MetadataResponseBody.Node replicaNode = new MetadataResponseBody.Node();
                    replicaNode.setId(payload.getInt());
                    replicaNodes.add(replicaNode);
                }
                partition.setReplicaNodes(replicaNodes);

                int isrNodeSize = payload.getInt();
                partition.setIsrNodeSize(isrNodeSize);
                List<MetadataResponseBody.Node> isrNodes = new ArrayList<>();
                for (int k = 0; k < isrNodeSize; k++) {
                    MetadataResponseBody.Node isrNode = new MetadataResponseBody.Node();
                    isrNode.setId(payload.getInt());
                    isrNodes.add(isrNode);
                }
                partition.setIsrNodes(isrNodes);

                int offlineNodeSize = payload.getInt();
                partition.setIsrNodeSize(offlineNodeSize);
                List<MetadataResponseBody.Node> offlineNodes = new ArrayList<>();
                for (int k = 0; k < offlineNodeSize; k++) {
                    MetadataResponseBody.Node offlineNode = new MetadataResponseBody.Node();
                    offlineNode.setId(payload.getInt());
                    offlineNodes.add(offlineNode);
                }
                partition.setIsrNodes(offlineNodes);


                partitions.add(partition);
            }
            topic.setPartitions(partitions);
            topics.add(topic);
        }
        metadataResponseBody.setTopics(topics);

        System.out.println("metadataRequestBody 解析结果为： \n"+ metadataResponseBody);
        return metadataResponseBody;
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
        return false;
    }

}
