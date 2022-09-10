package com.itranlin.reimu.common.bean;

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

    public synchronized static <T> RequestResult<T> e() {
        return RequestResult.e(StatusEnum.OK);
    }
    public synchronized static <T> RequestResult<T> e(StatusEnum statusEnum) {
        return RequestResult.e(statusEnum,null);
    }

    public synchronized static <T> RequestResult<T> e(T data) {
        return RequestResult.e(StatusEnum.OK,data);
    }

    public synchronized static <T> RequestResult<T> e(StatusEnum statusEnum, T data) {
        return RequestResult.e(statusEnum,data,statusEnum.msg);
    }

    public synchronized static <T> RequestResult<T> e(StatusEnum statusEnum, T data, String message) {
        return RequestResult.<T>builder().status(statusEnum.code)
                .msg(message).data(data).build();
    }

    public synchronized static <T> RequestResult<T> e(String code, T data, String message) {
        return RequestResult.e(StatusEnum.OK,data,message);
    }
}
