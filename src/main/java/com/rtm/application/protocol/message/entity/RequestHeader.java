package com.rtm.application.protocol.message.entity;

import com.rtm.application.protocol.message.entity.api.ApiVersionInfo;
import com.rtm.application.protocol.message.entity.api.Field;

import java.util.List;
import java.util.Objects;

/**
 *  Kafka 协议请求约束
 *  <ul>
 *    <p>
 *        Request Header v2 => request_api_key request_api_version correlation_id client_id TAG_BUFFER
 *    </p>
 *    <p>
 *         request_api_key => INT16
 *    </p>
 *    <p>
 *        request_api_version => INT16
 *    </p>
 *    <p>
 *        correlation_id => INT32
 *    </p>
 *    <p>
 *        client_id => NULLABLE_STRING => INT16(length) + length(内容)
 *    </p>
 *  </ul>
 */
public class RequestHeader {

    /**
     *  前4个字节，message_size 字段给出了后续请求或响应消息的大小（以字节为单位）。
     *  客户端可以先将此 4 字节大小读取为整数 N，然后读取并解析请求的后续 N 个字节，
     *  以此来读取请求。
     *  INT32 = 4 byte
     */
    private int length;

    /**
     * INT16 = 2 byte
     */
    private short apiKey;

    /**
     * INT16 = 2 byte
     */
    private short apiVersion;

    /**
     * INT32 = 4 byte
     */
    private int correlationId;

    /**
     * INT16 = 2 byte
     */
    private String clientId;

    /**
     *  标记字段
     */
    private List<Field> taggedFields;

    /**
     *  Api 版本信息
     */
    private ApiVersionInfo apiVersionInfo;

    /**
     *  请求头的长度
     */
    private int headerLength;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public short getApiKey() {
        return apiKey;
    }

    public void setApiKey(short apiKey) {
        this.apiKey = apiKey;
    }

    public short getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(short apiVersion) {
        this.apiVersion = apiVersion;
    }

    public int getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(int correlationId) {
        this.correlationId = correlationId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<Field> getTaggedFields() {
        return taggedFields;
    }

    public void setTaggedFields(List<Field> taggedFields) {
        this.taggedFields = taggedFields;
    }

    public ApiVersionInfo getApiVersionInfo() {
        if (apiVersionInfo == null) {
            apiVersionInfo = new ApiVersionInfo(apiKey, apiVersion);
        }
        return apiVersionInfo;
    }

    public void setApiVersionInfo(ApiVersionInfo apiVersionInfo) {
        this.apiVersionInfo = apiVersionInfo;
    }

    public int getHeaderLength() {
        return headerLength;
    }

    public void setHeaderLength(int headerLength) {
        this.headerLength = headerLength;
    }

    @Override
    public int hashCode() {
        int result = apiKey;
        result = 31 * result + (int) apiVersion;
        result = 31 * result + correlationId;
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        return result;
    }

    /**
     *  根据 apiKey、apiVersion、correlationId、clientId 判断是否相等
     *  用于判断是否为同一个请求包
     * @param o 目标对象
     * @return 返回是否相等 true 相等, false 不相等
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestHeader that = (RequestHeader) o;

        if (apiKey != that.apiKey) return false;
        if (apiVersion != that.apiVersion) return false;
        if (correlationId != that.correlationId) return false;
        return Objects.equals(clientId, that.clientId);
    }

    @Override
    public String toString() {
        return "RequestHeader{" +
                "length=" + length +
                ", apiKey=" + apiKey +
                ", apiVersion=" + apiVersion +
                ", correlationId=" + correlationId +
                ", clientId='" + clientId + '\'' +
                ", taggedFields=" + taggedFields +
                ", apiVersionInfo=" + apiVersionInfo +
                ", headerLength=" + headerLength +
                '}';
    }
}
