package com.rtm.application.protocol.message.entity.api;

import java.util.List;

/**
 *
 * @author rtm
 */
public class ResponsePartition {

    private int partitionIndex;

    private short errorCode;

    private long highWatermark;

    private long lastStableOffset;

    private long logStartOffset;

    private int abortedTransactionsSize;

    private List<AbortedTransactions> abortedTransactions;

    private int preferredReadReplica;

    /**
     *  RecordBatch 总长度
     */
    private int recordsBatchLength;


    private List<RecordBatch> recordBatches;


    public int getPartitionIndex() {
        return partitionIndex;
    }

    public void setPartitionIndex(int partitionIndex) {
        this.partitionIndex = partitionIndex;
    }

    public short getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(short errorCode) {
        this.errorCode = errorCode;
    }

    public long getHighWatermark() {
        return highWatermark;
    }

    public void setHighWatermark(long highWatermark) {
        this.highWatermark = highWatermark;
    }

    public long getLastStableOffset() {
        return lastStableOffset;
    }

    public void setLastStableOffset(long lastStableOffset) {
        this.lastStableOffset = lastStableOffset;
    }

    public long getLogStartOffset() {
        return logStartOffset;
    }

    public void setLogStartOffset(long logStartOffset) {
        this.logStartOffset = logStartOffset;
    }

    public int getAbortedTransactionsSize() {
        return abortedTransactionsSize;
    }

    public void setAbortedTransactionsSize(int abortedTransactionsSize) {
        this.abortedTransactionsSize = abortedTransactionsSize;
    }

    public List<AbortedTransactions> getAbortedTransactions() {
        return abortedTransactions;
    }

    public void setAbortedTransactions(List<AbortedTransactions> abortedTransactions) {
        this.abortedTransactions = abortedTransactions;
    }

    public int getPreferredReadReplica() {
        return preferredReadReplica;
    }

    public void setPreferredReadReplica(int preferredReadReplica) {
        this.preferredReadReplica = preferredReadReplica;
    }

    public int getRecordsBatchLength() {
        return recordsBatchLength;
    }

    public void setRecordsBatchLength(int recordsBatchLength) {
        this.recordsBatchLength = recordsBatchLength;
    }

    public List<RecordBatch> getRecordBatches() {
        return recordBatches;
    }

    public void setRecordBatches(List<RecordBatch> recordBatches) {
        this.recordBatches = recordBatches;
    }

    @Override
    public String toString() {
        return "ResponsePartition{" +
                "partitionIndex=" + partitionIndex +
                ", errorCode=" + errorCode +
                ", highWatermark=" + highWatermark +
                ", lastStableOffset=" + lastStableOffset +
                ", logStartOffset=" + logStartOffset +
                ", abortedTransactionsSize=" + abortedTransactionsSize +
                ", abortedTransactions=" + abortedTransactions +
                ", preferredReadReplica=" + preferredReadReplica +
                ", recordsBatchLength=" + recordsBatchLength +
                ", recordBatches=" + recordBatches +
                '}';
    }
}
