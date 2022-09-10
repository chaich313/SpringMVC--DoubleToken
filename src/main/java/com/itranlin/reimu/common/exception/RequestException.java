package com.itranlin.reimu.common.exception;


import com.itranlin.reimu.common.bean.StatusEnum;

import lombok.*;

import java.io.Serializable;

/**
 * @author itranlin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestException extends RuntimeException implements Serializable {
    private String status;
    private String msg;
    private Exception e;

    public RequestException(StatusEnum statusEnum, String msg) {
        this.status = statusEnum.code;
        this.msg = msg;
    }

    public RequestException(StatusEnum statusEnum, String msg, Exception e) {
        this.status = statusEnum.code;
        this.msg = msg;
        this.e = e;
    }

    public RequestException(StatusEnum statusEnum, Exception e) {
        this.status = statusEnum.code;
        this.msg = statusEnum.msg;
        this.e = e;
    }


    public RequestException(StatusEnum statusEnum) {
        this.status = statusEnum.code;
        this.msg = statusEnum.msg;
    }
}
