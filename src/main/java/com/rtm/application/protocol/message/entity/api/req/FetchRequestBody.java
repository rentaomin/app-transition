package com.rtm.application.protocol.message.entity.api.req;

import com.rtm.application.protocol.message.entity.RequestBody;
import com.rtm.application.protocol.message.entity.api.RequestTopic;
import java.util.List;

/**
 * FetchRequestBody 解析消息体.
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
 */
public class FetchRequestBody extends RequestBody  {


    /**
     *  replica_id => INT32, 该字段在版本 15（不包含）之前，详细可以查看
     *  https://kafka.apache.org/protocol#The_Messages_Fetch
     */
    private int replicaId;

    /**
     * 等待响应的最长时间（以毫秒为单位）,INT32
     */
    private int maxWaitTimeMs;

    /**
     * 响应中累积的最小字节数。 => INT32
     */
    private int minBytes;

    /**
     * 要获取的最大字节数 => INT32
     */
    private int maxBytes;

    /**
     *  此设置控制事务记录的可见性。使用 READ_UNCOMMITTED（isolation_level = 0）可使所有记录可见。使用 READ_COMMITTED（isolation_level = 1），
     *  非事务和已提交的事务记录均可见。更具体地说，READ_COMMITTED 返回小于当前 LSO（最后稳定偏移量）偏移量的所有数据，
     *  并允许在结果中包含中止事务列表，这允许消费者丢弃 ABORTED 事务记录, => INT8
     */
    private byte isolationLevel;

    /**
     * 获取会话 ID。=> INT32
     */
    private int sessionId;

    /**
     * 获取会话时期，用于对会话中的请求进行排序。=> INT32
     */
    private int sessionEpoch;

    /**
     * 获取主题列表。
     */
    private List<RequestTopic> topics;

    /**
     *  主题列表的数量， => INT32
     */
    private int topicSize;

    /**
     *  要删除的分区的数量。 => INT32
     */
    private int forgottenTopicsDataSize;

    /**
     *  在增量提取请求中，要删除的分区。
     */
    private List<RequestTopic> forgottenTopicsData;

    public int getReplicaId() {
        return replicaId;
    }

    public void setReplicaId(int replicaId) {
        this.replicaId = replicaId;
    }

    public int getMaxWaitTimeMs() {
        return maxWaitTimeMs;
    }

    public void setMaxWaitTimeMs(int maxWaitTimeMs) {
        this.maxWaitTimeMs = maxWaitTimeMs;
    }

    public int getMinBytes() {
        return minBytes;
    }

    public void setMinBytes(int minBytes) {
        this.minBytes = minBytes;
    }

    public int getMaxBytes() {
        return maxBytes;
    }

    public void setMaxBytes(int maxBytes) {
        this.maxBytes = maxBytes;
    }

    public byte getIsolationLevel() {
        return isolationLevel;
    }

    public void setIsolationLevel(byte isolationLevel) {
        this.isolationLevel = isolationLevel;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getSessionEpoch() {
        return sessionEpoch;
    }

    public void setSessionEpoch(int sessionEpoch) {
        this.sessionEpoch = sessionEpoch;
    }

    public List<RequestTopic> getTopics() {
        return topics;
    }

    public void setTopics(List<RequestTopic> topics) {
        this.topics = topics;
    }

    public int getTopicSize() {
        return topicSize;
    }

    public void setTopicSize(int topicSize) {
        this.topicSize = topicSize;
    }

    public int getForgottenTopicsDataSize() {
        return forgottenTopicsDataSize;
    }

    public void setForgottenTopicsDataSize(int forgottenTopicsDataSize) {
        this.forgottenTopicsDataSize = forgottenTopicsDataSize;
    }

    public List<RequestTopic> getForgottenTopicsData() {
        return forgottenTopicsData;
    }

    public void setForgottenTopicsData(List<RequestTopic> forgottenTopicsData) {
        this.forgottenTopicsData = forgottenTopicsData;
    }

    public String toString() {
        return "FetchRequestBody{" +
                "replicaId=" + replicaId +
                ", maxWaitTimeMs=" + maxWaitTimeMs +
                ", minBytes=" + minBytes +
                ", maxBytes=" + maxBytes +
                ", isolationLevel=" + isolationLevel +
                ", sessionId=" + sessionId +
                ", sessionEpoch=" + sessionEpoch +
                ", topics=" + topics +
                ", topicSize=" + topicSize +
               ", forgottenTopicsDataSize=" + forgottenTopicsDataSize +
                ", forgottenTopicsData=" + forgottenTopicsData +
                '}';
    }


}
