package com.rtm.application.util;

import com.rtm.application.util.enums.CodeEnums;
import lombok.Getter;
import lombok.Setter;

/**
 * 类功能描述：
 *
 * @author Administrator
 * @date 2023/07/26
 */
@Getter
@Setter
public final class Response<T> {

    private static final long serialVersionUID = 3463670305311726018L;

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应信息，成功或失败
     */
    private String msg;

    /**
     *  返回的数据
     */
    private T data;

    private Response() {
    }

    public static Response builder() {
        return new Response();
    }

    private Response config(int code, String msg, T data) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
        return this;
    }


    public Response success() {
        this.success(null);
        return this;
    }

    public Response success(T data) {
        this.config(CodeEnums.SUCCESS.getCode(), CodeEnums.SUCCESS.getDesc(), data);
        return this;
    }

    public Response success(String msg, T data) {
        this.config(CodeEnums.SUCCESS.getCode(), msg, data);
        return this;
    }

    public Response success(int code, String msg, T data) {
        this.config(code, msg, data);
        return this;
    }

    public Response failure() {
        this.failure(null);
        return this;
    }

    public Response failure(T data) {
        this.failure(CodeEnums.FAILURE.getCode(),CodeEnums.FAILURE.getDesc(),data);
        return this;
    }

    public Response failure(String msg, T data) {
        this.failure(CodeEnums.FAILURE.getCode(), msg, data);
        return this;
    }

    public Response failure(int code, String msg, T data) {
        this.config(code, msg, data);
        return this;
    }
}
