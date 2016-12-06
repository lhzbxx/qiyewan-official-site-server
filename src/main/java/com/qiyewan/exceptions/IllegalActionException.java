package com.qiyewan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by lhzbxx on 2016/10/27.
 *
 * 非法操作
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IllegalActionException extends RuntimeException {
    public IllegalActionException(String message) {
        super(message);
    }
}
