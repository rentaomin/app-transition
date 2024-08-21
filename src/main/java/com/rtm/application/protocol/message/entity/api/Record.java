package com.rtm.application.protocol.message.entity.api;

import java.util.List;

/**
 *
 *  协议数据结构约束：
 *  <p>  预留字段，客户端不传输该字段
 *  version: int16 (current version is 0)
 *  type: int16 (0 indicates an abort marker, 1 indicates a commit)
 *  </p>
 *  <p>
 *  length: varint
 * attributes: int8
 *     bit 0~7: unused
 * timestampDelta: varlong
 * offsetDelta: varint
 * keyLength: varint
 * key: byte[]
 * valueLen: varint
 * value: byte[]
 * Headers => [Header]
 *
 * </p>
 */
public class Record {

    private short version;

    private short type;

    private int length;

    private byte attributes;

    private long timestampDelta;

    private int offsetDelta;

    private int keyLength;

    private String key;

    private int valueLength;

    private String value;

    private int headersSize;

    private List<RecordHeader> headers;

    public short getVersion() {
        return version;
    }

    public void setVersion(short version) {
        this.version = version;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte getAttributes() {
        return attributes;
    }

    public void setAttributes(byte attributes) {
        this.attributes = attributes;
    }

    public long getTimestampDelta() {
        return timestampDelta;
    }

    public void setTimestampDelta(long timestampDelta) {
        this.timestampDelta = timestampDelta;
    }

    public int getOffsetDelta() {
        return offsetDelta;
    }

    public void setOffsetDelta(int offsetDelta) {
        this.offsetDelta = offsetDelta;
    }

    public int getKeyLength() {
        return keyLength;
    }

    public void setKeyLength(int keyLength) {
        this.keyLength = keyLength;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValueLength() {
        return valueLength;
    }

    public void setValueLength(int valueLength) {
        this.valueLength = valueLength;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getHeadersSize() {
        return headersSize;
    }

    public void setHeadersSize(int headersSize) {
        this.headersSize = headersSize;
    }

    public List<RecordHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<RecordHeader> headers) {
        this.headers = headers;
    }

    @Override
    public String toString() {
        return "Record{" +
                "version=" + version +
                ", type=" + type +
                ", length=" + length +
                ", attributes=" + attributes +
                ", timestampDelta=" + timestampDelta +
                ", offsetDelta=" + offsetDelta +
                ", keyLength=" + keyLength +
                ", key='" + key + '\'' +
                ", valueLength=" + valueLength +
                ", value='" + value + '\'' +
                ", headersSize=" + headersSize +
                ", headers=" + headers +
                '}';
    }
}
