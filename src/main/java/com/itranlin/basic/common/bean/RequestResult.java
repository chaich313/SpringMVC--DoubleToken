package com.itranlin.basic.common.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author itranlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestResult<T> implements Serializable {

    private final long timestamps = System.currentTimeMillis();
    private String status;
    private T data;
    private String msg;

    public synchronized static <T> RequestResult<T> e(StatusEnum statusEnum) {
        return RequestResult.<T>builder().status(statusEnum.code)
                .msg(statusEnum.msg).data(null).build();
    }

    public synchronized static <T> RequestResult<T> e(StatusEnum statusEnum, T data) {
        return RequestResult.<T>builder().status(statusEnum.code)
                .msg(statusEnum.msg).data(data).build();
    }

    public synchronized static <T> RequestResult<T> e(StatusEnum statusEnum, T data, String message) {
        return RequestResult.<T>builder().status(statusEnum.code)
                .msg(message).data(data).build();
    }

    public synchronized static <T> RequestResult<T> e(String code, T data, String message) {
        return RequestResult.<T>builder().status(code)
                .msg(message).data(data).build();
    }
}
