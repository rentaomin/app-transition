package com.rtm.application.protocol.api.parser.res;

import com.rtm.application.protocol.KafkaProtocolParser;
import com.rtm.application.protocol.message.entity.api.*;
import com.rtm.application.protocol.message.entity.api.res.FetchResponseBody;
import com.rtm.application.protocol.message.enums.ApiKeys;
import com.rtm.application.protocol.message.enums.ApiVersion;
import com.rtm.application.protocol.message.exception.ProtocolParseException;
import com.rtm.application.protocol.util.ByteUtils;

import java.nio.ByteBuffer;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

/**
 * FetchResponseParser 协议 payload 解析器.
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
 * @author rtm
 * @date 2024/08/15
 */
public class FetchResponseParser implements KafkaProtocolParser<FetchResponseBody> {


    @Override
    public FetchResponseBody parsePacket(ByteBuffer payload, short version) throws ProtocolParseException {
        FetchResponseBody fetchResponseBody = new FetchResponseBody();
        fetchResponseBody.setThrottleTimeMs(payload.getInt());
        fetchResponseBody.setErrorCode(payload.getShort());
        fetchResponseBody.setSessionId(payload.getInt());
        int topicSize = payload.getInt();
        fetchResponseBody.setTopicSize(topicSize);
        List<ResponseTopic> topics = new ArrayList<>(topicSize);
        for (int i = 0; i < topicSize; i++) {
            ResponseTopic responseTopic = parseTopic(payload);
            topics.add(responseTopic);
        }
        fetchResponseBody.setTopics(topics);
        System.out.println("fetch api response 解析结果为：\n"+fetchResponseBody);
        return fetchResponseBody;
    }

    private ResponseTopic parseTopic(ByteBuffer payload) {
        ResponseTopic responseTopic = new ResponseTopic();

        // todo 暂时适配 string 类型，最新版本为 uuid
        short topicNameLength = payload.getShort();
        String topicName = ByteUtils.getString(payload, topicNameLength);
        responseTopic.setTopicId(topicName);
        int partitionSize = payload.getInt();
        responseTopic.setPartitionSize(partitionSize);
        List<ResponsePartition> partitions = new ArrayList<>(partitionSize);
        for (int i = 0; i < partitionSize; i++) {
            partitions.add(parsePartition(payload));
        }
        responseTopic.setPartitions(partitions);
        return responseTopic;
    }

    private ResponsePartition parsePartition(ByteBuffer payload) {
        ResponsePartition responsePartition = new ResponsePartition();
        responsePartition.setPartitionIndex(payload.getInt());
        responsePartition.setErrorCode(payload.getShort());
        responsePartition.setHighWatermark(payload.getLong());
        responsePartition.setLastStableOffset(payload.getLong());
        responsePartition.setLogStartOffset(payload.getLong());
        int abortedTransactionsSize = payload.getInt();
        if (abortedTransactionsSize > 0) {
            List<AbortedTransactions> abortedTransactions = new ArrayList<>(abortedTransactionsSize);
            for (int i = 0; i < abortedTransactionsSize; i++) {
                AbortedTransactions abortedTransaction = new AbortedTransactions();
                abortedTransaction.setProducerId(payload.getLong());
                abortedTransaction.setFirstOffset(payload.getLong());
                abortedTransactions.add(abortedTransaction);
            }
        }
        responsePartition.setAbortedTransactionsSize(abortedTransactionsSize);
//        responsePartition.setPreferredReadReplica(payload.getInt());
        int recordBatchLength = payload.getInt();
        responsePartition.setRecordsBatchLength(recordBatchLength);

        // 从这里开始读取Record Batch信息
        ByteBuffer recordBatchBuffer = payload.slice();
        recordBatchBuffer.limit(recordBatchLength);
        // 解析所有的Record Batch
        List<RecordBatch> recordBatches = new ArrayList<>(recordBatchLength);
        while (recordBatchBuffer.remaining() > 0) {
            RecordBatch recordBatch = parseRecordBatch(recordBatchBuffer);
            recordBatches.add(recordBatch);
            // 处理recordBatch
        }
        responsePartition.setRecordBatches(recordBatches);

//        List<RecordBatch> recordBatches = new ArrayList<>(recordBatchLength);
//        if (recordBatchLength > 0) {
//            for (int i = 0; i < recordBatchLength; i++) {
//                RecordBatch recordBatch = parseRecordBatch(payload);
//                recordBatches.add(recordBatch);
//            }
//            responsePartition.setRecordBatches(recordBatches);
//        }
        return responsePartition;
    }

    private RecordBatch parseRecordBatch(ByteBuffer payload) {
        RecordBatch recordBatch = new RecordBatch();
        recordBatch.setBaseOffset(payload.getLong());
        recordBatch.setBatchLength(payload.getInt());
        recordBatch.setPartitionLeaderEpoch(payload.getInt());
        recordBatch.setMagic(payload.get());
//        recordBatch.setCrc(ByteUtils.readUnsignedVarint(payload));
        recordBatch.setCrc(payload.getInt());
        recordBatch.setLastOffsetDelta(payload.getInt());
        recordBatch.setBaseTimestamp(payload.getLong());
        recordBatch.setMaxTimestamp(payload.getLong());
        recordBatch.setProducerId(payload.getLong());
        recordBatch.setBaseSequence(payload.getInt());
        int recordSize = payload.getInt();
        recordBatch.setRecordsSize(recordSize);
        List<Record> records = new ArrayList<>(recordSize);
        if (recordSize > 0 ) {
            for (int i = 0; i < recordSize; i++) {
                Record record = parseRecord(payload);
                records.add(record);
            }
        }
        recordBatch.setRecord(records);
        return recordBatch;
    }

    private Record parseRecord(ByteBuffer payload) {
        Record record = new Record();
        record.setLength(ByteUtils.readVarint(payload));
        record.setAttributes(payload.get());
        record.setTimestampDelta(ByteUtils.readVarlong(payload));
        System.out.println("时间： "+ DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL));
        record.setOffsetDelta(ByteUtils.readVarint(payload));
        int keyLength = ByteUtils.readVarint(payload);
        record.setKeyLength(keyLength);
        if (keyLength > 0) {
            record.setKey(ByteUtils.getString(payload, keyLength));
        }
        int valueLength = ByteUtils.readVarint(payload);
        record.setValueLength(valueLength);
        if (valueLength > 0) {
            record.setValue(ByteUtils.getString(payload, valueLength));
        }
        byte headerSize = payload.get();
        record.setHeadersSize(headerSize);
        List<RecordHeader> headers = new ArrayList<>(headerSize);
        for (byte i = 0; i < headerSize; i++) {
            headers.add(parseHeader(payload));
        }
        record.setHeaders(headers);
        return record;
    }

    private RecordHeader parseHeader(ByteBuffer payload) {
        RecordHeader recordHeader = new RecordHeader();
        int headerKeyLength = ByteUtils.readVarint(payload);
        recordHeader.setHeaderKeyLength(headerKeyLength);
        if (headerKeyLength > 0) {
            recordHeader.setHeaderKey(ByteUtils.getString(payload, headerKeyLength));
        }
        int headerValueLength = ByteUtils.readVarint(payload);
        recordHeader.setHeaderValueLength(headerValueLength);
        if (headerValueLength > 0) {
            recordHeader.setHeaderValue(ByteUtils.getString(payload, headerValueLength));
        }
        return recordHeader;
    }


    @Override
    public short getMinVersion() {
        return ApiVersion.V16.getVersion();
    }

    @Override
    public short getMaxVersion() {
        return ApiVersion.V16.getVersion();
    }


    @Override
    public short getApiKey() {
        return ApiKeys.FETCH.getCode();
    }

    @Override
    public boolean isRequestParser() {
        return false;
    }

}
