package com.qiyewan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by lhzbxx on 2016/10/27.
 *
 * 未知错误
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class UnknownException extends RuntimeException {
    public UnknownException(String message) {
        super(message);
    }
}
