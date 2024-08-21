package com.rtm.application.protocol.message.entity.api;

/**
 *  分区信息
 */
public class RequestPartition {


    /**
     * 分区索引. => INT32
     */
    private int partition;

    /**
     *  分区的当前领导者时期。=> INT32
     */
    private int currentLeaderEpoch;

    /**
     *  消息偏移量。 => INT64
     */
    private long fetchOffset;

    /**
     *  跟随者副本的最早可用偏移量。仅当请求由跟随者发送时才使用此字段。=> INT64
     */
    private long logStartOffset;

    /**
     *  分区的最大字节数。=> INT32
     */
    private int partitionMaxBytes;


    public int getPartition() {
        return partition;
    }

    public void setPartition(int partition) {
        this.partition = partition;
    }

    public int getCurrentLeaderEpoch() {
        return currentLeaderEpoch;
    }

    public void setCurrentLeaderEpoch(int currentLeaderEpoch) {
        this.currentLeaderEpoch = currentLeaderEpoch;
    }

    public long getFetchOffset() {
        return fetchOffset;
    }

    public void setFetchOffset(long fetchOffset) {
        this.fetchOffset = fetchOffset;
    }


    public long getLogStartOffset() {
        return logStartOffset;
    }

    public void setLogStartOffset(long logStartOffset) {
        this.logStartOffset = logStartOffset;
    }

    public int getPartitionMaxBytes() {
        return partitionMaxBytes;
    }

    public void setPartitionMaxBytes(int partitionMaxBytes) {
        this.partitionMaxBytes = partitionMaxBytes;
    }

    public String toString() {
        return "Partition{" +
                "partition=" + partition +
                ", currentLeaderEpoch=" + currentLeaderEpoch +
                ", fetchOffset=" + fetchOffset +
                ", logStartOffset=" + logStartOffset +
                ", partitionMaxBytes=" + partitionMaxBytes +
                '}';
    }
}
