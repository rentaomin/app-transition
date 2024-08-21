package com.rtm.application.protocol.message.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 我们使用数字代码来指示服务器上发生了哪些问题。客户端可以将这些代码转换为异常或
 * 客户端语言中适当的错误处理机制。以下是当前使用的错误代码表
 */
public enum ApiErrorCode {
    UNKNOWN_SERVER_ERROR(-1, "服务器在处理请求时遇到意外错误"),
    NONE(0, "错误"),
    OFFSET_OUT_OF_RANGE(1, "请求的偏移量不在服务器维护的偏移量范围内。"),
    CORRUPT_MESSAGE(2, "此消息的 CRC 校验失败、超出有效大小、压缩主题的密钥为空或已损坏。"),
    UNKNOWN_TOPIC_OR_PARTITION(3, "此服务器不托管此主题分区"),
    INVALID_FETCH_SIZE(4, "请求的提取大小无效。"),
    LEADER_NOT_AVAILABLE(5, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    NOT_LEADER_OR_FOLLOWER(6, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    REQUEST_TIMED_OUT(7, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    BROKER_NOT_AVAILABLE(8, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    REPLICA_NOT_AVAILABLE(9, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    MESSAGE_TOO_LARGE(10, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    STALE_CONTROLLER_EPOCH(11, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    OFFSET_METADATA_TOO_LARGE(12, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    NETWORK_EXCEPTION(13, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    COORDINATOR_LOAD_IN_PROGRESS(14, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    COORDINATOR_NOT_AVAILABLE(15, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    NOT_COORDINATOR(16, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    INVALID_TOPIC_EXCEPTION(17, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    RECORD_LIST_TOO_LARGE(18, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    NOT_ENOUGH_REPLICAS(19, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    NOT_ENOUGH_REPLICAS_AFTER_APPEND(20, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    INVALID_REQUIRED_ACKS(21, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    ILLEGAL_GENERATION(22, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    INCONSISTENT_GROUP_PROTOCOL(23, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    INVALID_GROUP_ID(24, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    UNKNOWN_MEMBER_ID(25, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    INVALID_SESSION_TIMEOUT(26, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    REBALANCE_IN_PROGRESS(27, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    INVALID_COMMIT_OFFSET_SIZE(28, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    TOPIC_AUTHORIZATION_FAILED(29, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    GROUP_AUTHORIZATION_FAILED(30, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    CLUSTER_AUTHORIZATION_FAILED(31, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    INVALID_TIMESTAMP(32, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    UNSUPPORTED_SASL_MECHANISM(33, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    ILLEGAL_SASL_STATE(34, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    UNSUPPORTED_VERSION(35, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    TOPIC_ALREADY_EXISTS(36, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    INVALID_PARTITIONS(37, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    INVALID_REPLICATION_FACTOR(38, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    INVALID_REPLICA_ASSIGNMENT(39, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    INVALID_CONFIG(40, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    NOT_CONTROLLER(41, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    INVALID_REQUEST(42, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    UNSUPPORTED_FOR_MESSAGE_FORMAT(43, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    POLICY_VIOLATION(44, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    OUT_OF_ORDER_SEQUENCE_NUMBER(45, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    DUPLICATE_SEQUENCE_NUMBER(46, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    INVALID_PRODUCER_EPOCH(47, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    INVALID_TXN_STATE(48, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    INVALID_PRODUCER_ID_MAPPING(49, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    INVALID_TRANSACTION_TIMEOUT(50, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    CONCURRENT_TRANSACTIONS(51, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    TRANSACTION_COORDINATOR_FENCED(52, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    TRANSACTIONAL_ID_AUTHORIZATION_FAILED(53, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    SECURITY_DISABLED(54, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    OPERATION_NOT_ATTEMPTED(55, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    KAFKA_STORAGE_ERROR(56, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    LOG_DIR_NOT_FOUND(57, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    SASL_AUTHENTICATION_FAILED(58, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    UNKNOWN_PRODUCER_ID(59, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    REASSIGNMENT_IN_PROGRESS(60, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    DELEGATION_TOKEN_AUTH_DISABLED(61, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    DELEGATION_TOKEN_NOT_FOUND(62, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    DELEGATION_TOKEN_OWNER_MISMATCH(63, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    DELEGATION_TOKEN_REQUEST_NOT_ALLOWED(64, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    DELEGATION_TOKEN_AUTHORIZATION_FAILED(65, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    DELEGATION_TOKEN_EXPIRED(66, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    INVALID_PRINCIPAL_TYPE(67, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    NON_EMPTY_GROUP(68, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    GROUP_ID_NOT_FOUND(69, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    FETCH_SESSION_ID_NOT_FOUND(70, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    INVALID_FETCH_SESSION_EPOCH(71, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    LISTENER_NOT_FOUND(72, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    TOPIC_DELETION_DISABLED(73, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    FENCED_LEADER_EPOCH(74, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    UNKNOWN_LEADER_EPOCH(75, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    UNSUPPORTED_COMPRESSION_TYPE(76, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    STALE_BROKER_EPOCH(77, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    OFFSET_NOT_AVAILABLE(78, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    MEMBER_ID_REQUIRED(79, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    PREFERRED_LEADER_NOT_AVAILABLE(80, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    GROUP_MAX_SIZE_REACHED(81, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    FENCED_INSTANCE_ID(82, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    ELIGIBLE_LEADERS_NOT_AVAILABLE(83, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    ELECTION_NOT_NEEDED(84, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    NO_REASSIGNMENT_IN_PROGRESS(85, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    GROUP_SUBSCRIBED_TO_TOPIC(86, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    INVALID_RECORD(87, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    UNSTABLE_OFFSET_COMMIT(88, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    THROTTLING_QUOTA_EXCEEDED(89, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    PRODUCER_FENCED(90, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    RESOURCE_NOT_FOUND(91, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    DUPLICATE_RESOURCE(92, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    UNACCEPTABLE_CREDENTIAL(93, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    INCONSISTENT_VOTER_SET(94, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    INVALID_UPDATE_VERSION(95, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    FEATURE_UPDATE_FAILED(96, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    PRINCIPAL_DESERIALIZATION_FAILURE(97, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    SNAPSHOT_NOT_FOUND(98, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    POSITION_OUT_OF_RANGE(99, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    UNKNOWN_TOPIC_ID(100, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    DUPLICATE_BROKER_REGISTRATION(101, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    BROKER_ID_NOT_REGISTERED(102, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    INCONSISTENT_TOPIC_ID(103, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    INCONSISTENT_CLUSTER_ID(104, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    TRANSACTIONAL_ID_NOT_FOUND(105, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    FETCH_SESSION_TOPIC_ID_ERROR(106, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    INELIGIBLE_REPLICA(107, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    NEW_LEADER_ELECTED(108, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    OFFSET_MOVED_TO_TIERED_STORAGE(109, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    FENCED_MEMBER_EPOCH(110, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    UNRELEASED_INSTANCE_ID(111, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    UNSUPPORTED_ASSIGNOR(112, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    STALE_MEMBER_EPOCH(113, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    MISMATCHED_ENDPOINT_TYPE(114, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    UNSUPPORTED_ENDPOINT_TYPE(115, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    UNKNOWN_CONTROLLER_ID(116, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    UNKNOWN_SUBSCRIPTION_ID(117, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    TELEMETRY_TOO_LARGE(118, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    INVALID_REGISTRATION(119, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    TRANSACTION_ABORTABLE(120, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),

    ;


    private int code;

    private String description;

    private static final Map<Integer, ApiErrorCode> errorCodeMap;

    static {
        errorCodeMap = Arrays.stream(ApiErrorCode.values())
                .collect(Collectors.toMap(ApiErrorCode::getCode, Function.identity()));
    }


    private ApiErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static List<Integer> errorCodes() {
        return Arrays.stream(ApiErrorCode.values())
                .map(ApiErrorCode::getCode)
                .collect(Collectors.toList());
    }

    public static boolean contains(int code) {
        return errorCodes().contains(code);
    }

    public static String getDescription(int code) {
        return errorCodeMap.get(code).getDescription();
    }
}
