package com.rtm.application.protocol.message.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 以下是请求中的 ApiKey 对于以下每种请求类型可以采用的数字代码。
 *  与官方文档一致：https://kafka.apache.org/protocol#protocol_details
 */
public enum ApiKeysCode {

    REQUEST_HEADER((short)200,"RequestHeader"),
    RESPONSE_HEADER((short)300,"ResponseHeader"),

    PRODUCE((short) 0,"PRODUCE"),
    FETCH((short) 1,"FETCH"),
    ListOffsets((short) 2, "ListOffsets"),
    Metadata((short) 3, "Metadata"),
    LeaderAndIsr((short) 4, "LeaderAndIsr"),
    StopReplica((short) 5, "StopReplica"),
    UpdateMetadata((short) 6, "UpdateMetadata"),
    ControlledShutdown((short) 7, "ControlledShutdown"),
    OffsetCommit((short) 8, "OffsetCommit"),
    OffsetFetch((short) 9, "OffsetFetch"),
    FindCoordinator((short) 10, "FindCoordinator"),
    JoinGroup((short) 11, "JoinGroup"),
    Heartbeat((short) 12, "Heartbeat"),
    LeaveGroup((short) 13, "LeaveGroup"),
    SyncGroup((short) 14, "SyncGroup"),
    DescribeGroups((short) 15, "DescribeGroups"),
    ListGroups((short) 16, "ListGroups"),
    SaslHandshake((short) 17, "SaslHandshake"),
    ApiVersions((short) 18, "ApiVersions"),
    CreateTopics((short) 19, "CreateTopics"),
    DeleteTopics((short) 20, "DeleteTopics"),
    DeleteRecords((short) 21, "DeleteRecords"),
    InitProducerId((short) 22, "InitProducerId"),
    OffsetForLeaderEpoch((short) 23, "OffsetForLeaderEpoch"),
    AddPartitionsToTxn((short) 24, "AddPartitionsToTxn"),
    AddOffsetsToTxn((short) 25, "AddOffsetsToTxn"),
    EndTxn((short) 26, "EndTxn"),
    WriteTxnMarkers((short) 27, "WriteTxnMarkers"),
    TxnOffsetCommit((short) 28, "TxnOffsetCommit"),
    DescribeAcls((short) 29, "DescribeAcls"),
    CreateAcls((short) 30, "CreateAcls"),
    DeleteAcls((short) 31, "DeleteAcls"),
    DescribeConfigs((short) 32, "DescribeConfigs"),
    AlterConfigs((short) 33, "AlterConfigs"),
    AlterReplicaLogDirs((short) 34, "AlterReplicaLogDirs"),
    DescribeLogDirs((short) 35, "DescribeLogDirs"),
    SaslAuthenticate((short) 36, "SaslAuthenticate"),
    CreatePartitions((short) 37, "CreatePartitions"),
    CreateDelegationToken((short) 38, "CreateDelegationToken"),
    RenewDelegationToken((short) 39, "RenewDelegationToken"),
    ExpireDelegationToken((short) 40, "ExpireDelegationToken"),
    DescribeDelegationToken((short) 41, "DescribeDelegationToken"),
    DeleteGroups((short) 42, "DeleteGroups"),
    ElectLeaders((short) 43, "ElectLeaders"),
    IncrementalAlterConfigs((short) 44, "IncrementalAlterConfigs"),
    AlterPartitionReassignments((short) 45, "AlterPartitionReassignments"),
    ListPartitionReassignments((short) 46, "ListPartitionReassignments"),
    OffsetDelete((short) 47, "OffsetDelete"),
    DescribeClientQuotas((short) 48, "DescribeClientQuotas"),
    AlterClientQuotas((short) 49, "AlterClientQuotas"),
    DescribeUserScramCredentials((short) 50, "DescribeUserScramCredentials"),
    AlterUserScramCredentials((short) 51, "AlterUserScramCredentials"),

    DescribeQuorum((short) 55, "DescribeQuorum"),
    AlterPartition((short) 56, "AlterPartition"),
    UpdateFeatures((short) 57, "UpdateFeatures"),

    DescribeCluster((short) 60, "DescribeCluster"),
    DescribeProducers((short) 61, "DescribeProducers"),

    UnregisterBroker((short) 64, "UnregisterBroker"),
    DescribeTransactions((short) 65, "DescribeTransactions"),
    ListTransactions((short) 66, "ListTransactions"),
    AllocateProducerIds((short) 67, "AllocateProducerIds"),
    ConsumerGroupHeartbeat((short) 68, "ConsumerGroupHeartbeat"),
    ConsumerGroupDescribe((short) 69, "ConsumerGroupDescribe"),

    GetTelemetrySubscriptions((short) 71, "GetTelemetrySubscriptions"),
    PushTelemetry((short) 72, "PushTelemetry"),
    ListClientMetricsResources((short) 74, "ListClientMetricsResources"),
    DescribeTopicPartitions((short) 75, "DescribeTopicPartitions")
    ;
    private short code;

    private String name;

    /**
     *  支持的 api key 映射表
     */
    private static final Map<Short, String> apiKeyCodeMap = Arrays.stream(ApiKeysCode.values())
            .collect(Collectors.toMap(ApiKeysCode::getCode, ApiKeysCode::getName));


    ApiKeysCode(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public short getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getName(Short apiKeyCode) {
        return apiKeyCodeMap.get(apiKeyCode);
    }


    public String getApiDescription(short apiKeyCode) {
        StringBuffer stringBuffer = new StringBuffer();
        return stringBuffer.append(getName(apiKeyCode))
                .append(" ( ")
                .append(apiKeyCode)
                .append(" ) ")
                .toString();
    }


    /**
     *  是否支持该 api key
     * @param apiKeyCode
     * @return
     */
    public static boolean contains(short apiKeyCode) {
        return apiKeyCodeMap.containsKey(apiKeyCode);
    }
}
