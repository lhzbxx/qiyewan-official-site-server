package com.qiyewan.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by lhzbxx on 2016/10/27.
 *
 * 缺少或异常权限
 */

@ResponseStatus(value = HttpStatus.NON_AUTHORITATIVE_INFORMATION)
public class NoAuthException extends RuntimeException {

    public NoAuthException(String message) {
        super(message);
    }

}
