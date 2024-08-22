package com.rtm.application.protocol.message.entity.api.res;

import com.rtm.application.protocol.message.entity.ResponseBody;
import java.util.List;

/**
 * MetadataResponseBody 解析消息体.
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
 */
public class MetadataResponseBody extends ResponseBody {

    private int throttleTimeMs;

    private int brokerSize;

    private List<Broker> brokers;

    private String clusterId;

    private int controllerId;

    private int topicSize;

    private List<MetadataTopic> topics;

    private int topicAuthorizedOperations;

    public int getThrottleTimeMs() {
        return throttleTimeMs;
    }

    public void setThrottleTimeMs(int throttleTimeMs) {
        this.throttleTimeMs = throttleTimeMs;
    }

    public int getBrokerSize() {
        return brokerSize;
    }

    public void setBrokerSize(int brokerSize) {
        this.brokerSize = brokerSize;
    }

    public List<Broker> getBrokers() {
        return brokers;
    }

    public void setBrokers(List<Broker> brokers) {
        this.brokers = brokers;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public int getControllerId() {
        return controllerId;
    }

    public void setControllerId(int controllerId) {
        this.controllerId = controllerId;
    }

    public int getTopicSize() {
        return topicSize;
    }

    public void setTopicSize(int topicSize) {
        this.topicSize = topicSize;
    }

    public List<MetadataTopic> getTopics() {
        return topics;
    }

    public void setTopics(List<MetadataTopic> topics) {
        this.topics = topics;
    }


    public int getTopicAuthorizedOperations() {
        return topicAuthorizedOperations;
    }

    public void setTopicAuthorizedOperations(int topicAuthorizedOperations) {
        this.topicAuthorizedOperations = topicAuthorizedOperations;
    }

    @Override
    public String toString() {
        return "MetadataResponseBody{" +
                "throttleTimeMs=" + throttleTimeMs +
                ", brokerSize=" + brokerSize +
                ", brokers=" + brokers +
                ", clusterId='" + clusterId + '\'' +
                ", controllerId=" + controllerId +
                ", topicSize=" + topicSize +
                ", topics=" + topics +
                ", topicAuthorizedOperations=" + topicAuthorizedOperations +
                '}';
    }

    static public class Broker {
        private int nodeId;

        //todo 待实现,以string 类型实现
        private short hostLength;

        private String host;
        private int port;

        //todo 待实现,以string 类型实现
        private short rackLength;

        private String rack;

        public int getNodeId() {
            return nodeId;
        }

        public void setNodeId(int nodeId) {
            this.nodeId = nodeId;
        }

        public short getHostLength() {
            return hostLength;
        }

        public void setHostLength(short hostLength) {
            this.hostLength = hostLength;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public short getRackLength() {
            return rackLength;
        }

        public void setRackLength(short rackLength) {
            this.rackLength = rackLength;
        }

        public String getRack() {
            return rack;
        }

        public void setRack(String rack) {
            this.rack = rack;
        }

        @Override
        public String toString() {
            return "Broker{" +
                    "nodeId=" + nodeId +
                    ", hostLength=" + hostLength +
                    ", host='" + host + '\'' +
                    ", port=" + port +
                    ", rackLength=" + rackLength +
                    ", rack='" + rack + '\'' +
                    '}';
        }
    }

    static public class MetadataTopic {
        private short errorCode;

        //todo 待实现,以string 类型实现
        private short nameLength;
        private String name;

        private String topicId;

        private boolean isInternal;

        private int partitionSize;

        private List<MetadataPartition> partitions;

        private int topicAuthorizedOperations;

        public short getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(short errorCode) {
            this.errorCode = errorCode;
        }

        public short getNameLength() {
            return nameLength;
        }

        public void setNameLength(short nameLength) {
            this.nameLength = nameLength;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTopicId() {
            return topicId;
        }

        public void setTopicId(String topicId) {
            this.topicId = topicId;
        }

        public boolean isInternal() {
            return isInternal;
        }

        public void setInternal(boolean internal) {
            isInternal = internal;
        }

        public int getPartitionSize() {
            return partitionSize;
        }

        public void setPartitionSize(int partitionSize) {
            this.partitionSize = partitionSize;
        }

        public List<MetadataPartition> getPartitions() {
            return partitions;
        }

        public void setPartitions(List<MetadataPartition> partitions) {
            this.partitions = partitions;
        }

        public int getTopicAuthorizedOperations() {
            return topicAuthorizedOperations;
        }

        public void setTopicAuthorizedOperations(int topicAuthorizedOperations) {
            this.topicAuthorizedOperations = topicAuthorizedOperations;
        }

        @Override
        public String toString() {
            return "MetadataTopic{" +
                    "errorCode=" + errorCode +
                    ", nameLength=" + nameLength +
                    ", name='" + name + '\'' +
                    ", topicId='" + topicId + '\'' +
                    ", isInternal=" + isInternal +
                    ", partitionSize=" + partitionSize +
                    ", partitions=" + partitions +
                    ", topicAuthorizedOperations=" + topicAuthorizedOperations +
                    '}';
        }
    }

    static public class MetadataPartition {

        private short errorCode;
        private int partitionIndex;

        private int leaderId;

        private int leaderEpoch;

        private int replicaNodeSize;

        private List<Node> replicaNodes;

        private int isrNodeSize;

        private List<Node> isrNodes;

        private int offlineReplicaSize;

        private List<Node> offlineReplicas;

        public short getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(short errorCode) {
            this.errorCode = errorCode;
        }

        public int getPartitionIndex() {
            return partitionIndex;
        }

        public void setPartitionIndex(int partitionIndex) {
            this.partitionIndex = partitionIndex;
        }

        public int getLeaderId() {
            return leaderId;
        }

        public void setLeaderId(int leaderId) {
            this.leaderId = leaderId;
        }

        public int getLeaderEpoch() {
            return leaderEpoch;
        }

        public void setLeaderEpoch(int leaderEpoch) {
            this.leaderEpoch = leaderEpoch;
        }

        public int getReplicaNodeSize() {
            return replicaNodeSize;
        }

        public void setReplicaNodeSize(int replicaNodeSize) {
            this.replicaNodeSize = replicaNodeSize;
        }

        public List<Node> getReplicaNodes() {
            return replicaNodes;
        }

        public void setReplicaNodes(List<Node> replicaNodes) {
            this.replicaNodes = replicaNodes;
        }

        public int getIsrNodeSize() {
            return isrNodeSize;
        }

        public void setIsrNodeSize(int isrNodeSize) {
            this.isrNodeSize = isrNodeSize;
        }

        public List<Node> getIsrNodes() {
            return isrNodes;
        }

        public void setIsrNodes(List<Node> isrNodes) {
            this.isrNodes = isrNodes;
        }

        public int getOfflineReplicaSize() {
            return offlineReplicaSize;
        }

        public void setOfflineReplicaSize(int offlineReplicaSize) {
            this.offlineReplicaSize = offlineReplicaSize;
        }

        public List<Node> getOfflineReplicas() {
            return offlineReplicas;
        }

        public void setOfflineReplicas(List<Node> offlineReplicas) {
            this.offlineReplicas = offlineReplicas;
        }

        @Override
        public String toString() {
            return "MetadataPartition{" +
                    "errorCode=" + errorCode +
                    ", partitionIndex=" + partitionIndex +
                    ", leaderId=" + leaderId +
                    ", leaderEpoch=" + leaderEpoch +
                    ", replicaNodeSize=" + replicaNodeSize +
                    ", replicaNodes=" + replicaNodes +
                    ", isrNodeSize=" + isrNodeSize +
                    ", isrNodes=" + isrNodes +
                    ", offlineReplicaSize=" + offlineReplicaSize +
                    ", offlineReplicas=" + offlineReplicas +
                    '}';
        }
    }

    static public class Node {
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    '}';
        }
    }
}
