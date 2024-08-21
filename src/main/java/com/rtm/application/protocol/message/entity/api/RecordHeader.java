package com.rtm.application.protocol.message.entity.api;


/**
 *
 * headerKeyLength: varint
 * headerKey: String
 * headerValueLength: varint
 * Value: byte[]
 */
public class RecordHeader {

    private int headerKeyLength;

    private String headerKey;

    private int headerValueLength;

    private String headerValue;

    public int getHeaderKeyLength() {
        return headerKeyLength;
    }

    public void setHeaderKeyLength(int headerKeyLength) {
        this.headerKeyLength = headerKeyLength;
    }

    public String getHeaderKey() {
        return headerKey;
    }

    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    public int getHeaderValueLength() {
        return headerValueLength;
    }

    public void setHeaderValueLength(int headerValueLength) {
        this.headerValueLength = headerValueLength;
    }

    public String getHeaderValue() {
        return headerValue;
    }

    public void setHeaderValue(String headerValue) {
        this.headerValue = headerValue;
    }

    @Override
    public String toString() {
        return "RecordHeader{" +
                "headerKeyLength=" + headerKeyLength +
                ", headerKey='" + headerKey + '\'' +
                ", headerValueLength=" + headerValueLength +
                ", headerValue='" + headerValue + '\'' +
                '}';
    }
}
