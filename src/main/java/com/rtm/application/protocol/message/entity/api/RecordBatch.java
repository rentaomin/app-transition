package com.rtm.application.protocol.message.entity.api;

import java.util.List;

/**
 *
 *  RecordBatch 数据格式：
 * baseOffset: int64
 * batchLength: int32
 * partitionLeaderEpoch: int32
 * magic: int8 (current magic value is 2)
 * crc: uint32
 * attributes: int16
 *     bit 0~2:
 *         0: no compression
 *         1: gzip
 *         2: snappy
 *         3: lz4
 *         4: zstd
 *     bit 3: timestampType
 *     bit 4: isTransactional (0 means not transactional)
 *     bit 5: isControlBatch (0 means not a control batch)
 *     bit 6: hasDeleteHorizonMs (0 means baseTimestamp is not set as the delete horizon for compaction)
 *     bit 7~15: unused
 * lastOffsetDelta: int32
 * baseTimestamp: int64
 * maxTimestamp: int64
 * producerId: int64
 * producerEpoch: int16
 * baseSequence: int32
 * records: [Record]
 */
public class RecordBatch {

    private long baseOffset;

    private int batchLength;

    private int partitionLeaderEpoch;

    private byte magic;

    private int crc;

    private short attributes;

    private int lastOffsetDelta;

    private long baseTimestamp;

    private long maxTimestamp;

    private long producerId;

    private short producerEpoch;

    private int baseSequence;

    private int recordsSize;

    private List<Record> record;

    public long getBaseOffset() {
        return baseOffset;
    }

    public void setBaseOffset(long baseOffset) {
        this.baseOffset = baseOffset;
    }

    public int getBatchLength() {
        return batchLength;
    }

    public void setBatchLength(int batchLength) {
        this.batchLength = batchLength;
    }

    public int getPartitionLeaderEpoch() {
        return partitionLeaderEpoch;
    }

    public void setPartitionLeaderEpoch(int partitionLeaderEpoch) {
        this.partitionLeaderEpoch = partitionLeaderEpoch;
    }

    public byte getMagic() {
        return magic;
    }

    public void setMagic(byte magic) {
        this.magic = magic;
    }

    public int getCrc() {
        return crc;
    }

    public void setCrc(int crc) {
        this.crc = crc;
    }

    public short getAttributes() {
        return attributes;
    }

    public void setAttributes(short attributes) {
        this.attributes = attributes;
    }

    public int getLastOffsetDelta() {
        return lastOffsetDelta;
    }

    public void setLastOffsetDelta(int lastOffsetDelta) {
        this.lastOffsetDelta = lastOffsetDelta;
    }

    public long getBaseTimestamp() {
        return baseTimestamp;
    }

    public void setBaseTimestamp(long baseTimestamp) {
        this.baseTimestamp = baseTimestamp;
    }

    public long getMaxTimestamp() {
        return maxTimestamp;
    }

    public void setMaxTimestamp(long maxTimestamp) {
        this.maxTimestamp = maxTimestamp;
    }

    public long getProducerId() {
        return producerId;
    }

    public void setProducerId(long producerId) {
        this.producerId = producerId;
    }

    public short getProducerEpoch() {
        return producerEpoch;
    }

    public void setProducerEpoch(short producerEpoch) {
        this.producerEpoch = producerEpoch;
    }

    public int getBaseSequence() {
        return baseSequence;
    }

    public void setBaseSequence(int baseSequence) {
        this.baseSequence = baseSequence;
    }

    public int getRecordsSize() {
        return recordsSize;
    }

    public void setRecordsSize(int recordsSize) {
        this.recordsSize = recordsSize;
    }

    public List<Record> getRecord() {
        return record;
    }

    public void setRecord(List<Record> record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "RecordBatch{" +
                "baseOffset=" + baseOffset +
                ", batchLength=" + batchLength +
                ", partitionLeaderEpoch=" + partitionLeaderEpoch +
                ", magic=" + magic +
                ", crc=" + crc +
                ", attributes=" + attributes +
                ", lastOffsetDelta=" + lastOffsetDelta +
                ", baseTimestamp=" + baseTimestamp +
                ", maxTimestamp=" + maxTimestamp +
                ", producerId=" + producerId +
                ", producerEpoch=" + producerEpoch +
                ", baseSequence=" + baseSequence +
                ", recordsSize=" + recordsSize +
                ", record=" + record +
                '}';
    }
}
