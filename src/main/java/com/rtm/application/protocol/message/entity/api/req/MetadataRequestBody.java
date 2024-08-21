package com.rtm.application.protocol.message.entity.api.req;

import com.rtm.application.protocol.message.entity.RequestBody;
import com.rtm.application.protocol.message.entity.api.RequestTopic;
import java.util.List;

/**
 * MetadataRequestBody 解析消息体.
 *
 * Metadata Request (Version: 12) => [topics] allow_auto_topic_creation include_topic_authorized_operations TAG_BUFFER
 *   topics => topic_id name TAG_BUFFER
 *     topic_id => UUID
 *     name => COMPACT_NULLABLE_STRING
 *   allow_auto_topic_creation => BOOLEAN
 *   include_topic_authorized_operations => BOOLEAN
 */
public class MetadataRequestBody extends RequestBody  {

    private int topicSize;

    private List<RequestTopic> topics;

    private boolean allowAutoTopicCreation;

    private boolean includeTopicAuthorizedOperations;

    public int getTopicSize() {
        return topicSize;
    }

    public void setTopicSize(int topicSize) {
        this.topicSize = topicSize;
    }

    public List<RequestTopic> getTopics() {
        return topics;
    }

    public void setTopics(List<RequestTopic> topics) {
        this.topics = topics;
    }

    public boolean isAllowAutoTopicCreation() {
        return allowAutoTopicCreation;
    }

    public void setAllowAutoTopicCreation(boolean allowAutoTopicCreation) {
        this.allowAutoTopicCreation = allowAutoTopicCreation;
    }

    public boolean isIncludeTopicAuthorizedOperations() {
        return includeTopicAuthorizedOperations;
    }

    public void setIncludeTopicAuthorizedOperations(boolean includeTopicAuthorizedOperations) {
        this.includeTopicAuthorizedOperations = includeTopicAuthorizedOperations;
    }

    @Override
    public String toString() {
        return "MetadataRequestBody{" +
                "topicSize=" + topicSize +
                ", topics=" + topics +
                ", allowAutoTopicCreation=" + allowAutoTopicCreation +
                ", includeTopicAuthorizedOperations=" + includeTopicAuthorizedOperations +
                '}';
    }
}
