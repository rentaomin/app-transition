package com.rtm.application.protocol.message.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 我们使用数字代码来指示服务器上发生了哪些问题。客户端可以将这些代码转换为异常或
 * 客户端语言中适当的错误处理机制。以下是当前使用的错误代码表
 */
public enum KafKaErrorCode {
    UNKNOWN_SERVER_ERROR(-1, "服务器在处理请求时遇到意外错误"),
    NONE(0, "错误"),
    OFFSET_OUT_OF_RANGE(1, "请求的偏移量不在服务器维护的偏移量范围内。"),
    CORRUPT_MESSAGE(2, "此消息的 CRC 校验失败、超出有效大小、压缩主题的密钥为空或已损坏。"),
    UNKNOWN_TOPIC_OR_PARTITION(3, "此服务器不托管此主题分区"),
    INVALID_FETCH_SIZE(4, "请求的提取大小无效。"),
    LEADER_NOT_AVAILABLE(5, "由于我们正处于领导者选举之中，因此该主题分区没有领导者。"),
    ;


    private int code;

    private String description;

    private KafKaErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public List<Integer> errorCodes() {
        return Arrays.stream(KafKaErrorCode.values())
                .map(KafKaErrorCode::getCode)
                .collect(Collectors.toList());
    }

    public boolean contains(int code) {
        return errorCodes().contains(code);
    }
}
