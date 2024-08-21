package com.rtm.application.protocol.message.entity.api.res;

import com.rtm.application.protocol.message.entity.ResponseBody;
import com.rtm.application.protocol.message.entity.api.ResponseTopic;

import java.util.List;

/**
 * FetchResponseBody 解析消息体.
 *  协议约束如：
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
public class FetchResponseBody extends ResponseBody  {

    private int throttleTimeMs;

    private short errorCode;

    private int sessionId;

    private int topicSize;

    private List<ResponseTopic> topics;

    public int getThrottleTimeMs() {
        return throttleTimeMs;
    }

    public void setThrottleTimeMs(int throttleTimeMs) {
        this.throttleTimeMs = throttleTimeMs;
    }

    public short getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(short errorCode) {
        this.errorCode = errorCode;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getTopicSize() {
        return topicSize;
    }

    public void setTopicSize(int topicSize) {
        this.topicSize = topicSize;
    }

    public List<ResponseTopic> getTopics() {
        return topics;
    }

    public void setTopics(List<ResponseTopic> topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "FetchResponseBody{" +
                "throttleTimeMs=" + throttleTimeMs +
                ", errorCode=" + errorCode +
                ", sessionId=" + sessionId +
                ", topicSize=" + topicSize +
                ", topics=" + topics +
                '}';
    }
}
