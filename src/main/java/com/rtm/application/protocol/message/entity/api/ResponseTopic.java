package com.rtm.application.protocol.message.entity.api;

import java.util.List;

/**
 *
 * 响应 topic 内容
 * <p>
 * Fetch Response (Version: 16) => throttle_time_ms error_code session_id [responses] TAG_BUFFER
 *   throttle_time_ms => INT32
 *   error_code => INT16
 *   session_id => INT32
 *   responses => topic_id [partitions] TAG_BUFFER
 *     topic_id => UUID
 *     partitions => partition_index error_code high_watermark last_stable_offset log_start_offset [aborted_transactions] preferred_read_replica records TAG_BUFFER
 *       partition_index => INT32
 *       error_code => INT16
 *       high_watermark => INT64
 *       last_stable_offset => INT64
 *       log_start_offset => INT64
 *       aborted_transactions => producer_id first_offset TAG_BUFFER
 *         producer_id => INT64
 *         first_offset => INT64
 *       preferred_read_replica => INT32
 *       records => COMPACT_RECORDS
 * </p>
 */
public class ResponseTopic {

    // 暂时支持 string
    private String topicId;

    private int partitionSize;

    private List<ResponsePartition> partitions;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public int getPartitionSize() {
        return partitionSize;
    }

    public void setPartitionSize(int partitionSize) {
        this.partitionSize = partitionSize;
    }

    public List<ResponsePartition> getPartitions() {
        return partitions;
    }

    public void setPartitions(List<ResponsePartition> partitions) {
        this.partitions = partitions;
    }

    @Override
    public String toString() {
        return "ResponseTopic{" +
                "topicId='" + topicId + '\'' +
                ", partitionSize=" + partitionSize +
                ", partitions=" + partitions +
                '}';
    }
}
