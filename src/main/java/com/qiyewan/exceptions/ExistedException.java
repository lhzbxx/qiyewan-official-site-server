package com.qiyewan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by lhzbxx on 2016/10/27.
 *
 * 重复异常
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ExistedException extends RuntimeException {
    public ExistedException(String message) {
        super(message);
    }
}
