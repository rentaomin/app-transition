package com.rtm.application.protocol.message.entity;

import com.rtm.application.protocol.message.entity.api.Field;
import java.util.List;

/**
 *  Kafka 协议响应请求头
 *  <ul>
 *    <p>
 *        Response Header v1 => correlation_id TAG_BUFFER
 *    </p>
 *    <p>
 *         correlation_id => INT32
 *    </p>
 *  </ul>
 */
public class ResponseHeader {

    /**
     *  前4个字节，message_size 字段给出了后续请求或响应消息的大小（以字节为单位）。
     *  客户端可以先将此 4 字节大小读取为整数 N，然后读取并解析请求的后续 N 个字节，
     *  以此来读取请求。
     *  INT32 = 4 byte
     */
    private int length;

    /**
     * INT32 = 4 byte
     */
    private int correlationId;

    /**
     *  标记字段
     */
    private List<Field> taggedFields;

    /**
     *  标记响应数据包请求头长度, => length(INT32) + correlationId(INT32)
     */
    private int headerLength;


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(int correlationId) {
        this.correlationId = correlationId;
    }

    public List<Field> getTaggedFields() {
        return taggedFields;
    }

    public void setTaggedFields(List<Field> taggedFields) {
        this.taggedFields = taggedFields;
    }


    public int getHeaderLength() {
        return headerLength;
    }

    public void setHeaderLength(int headerLength) {
        this.headerLength = headerLength;
    }
}
