package com.rtm.application.protocol.message.entity.api;

import java.util.List;

/**
 *  主题信息
 */
public class RequestTopic {

    /**
     * 唯一主题 ID, => UUID
     */
    private String topicId;

    private String topicName;

    /**
     * 获取分区列表。
     */
    private List<RequestPartition> partitions;

    /**
     *  分区的数量， => INT32
     */
    private int partitionSize;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }


    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<RequestPartition> getPartitions() {
        return partitions;
    }

    public void setPartitions(List<RequestPartition> partitions) {
        this.partitions = partitions;
    }

    public int getPartitionSize() {
        return partitionSize;
    }

    public void setPartitionSize(int partitionSize) {
        this.partitionSize = partitionSize;
    }

    @Override
    public String toString() {
        return "RequestTopic{" +
                "topicId='" + topicId + '\'' +
                ", topicName='" + topicName + '\'' +
                ", partitions=" + partitions +
                ", partitionSize=" + partitionSize +
                '}';
    }
}
