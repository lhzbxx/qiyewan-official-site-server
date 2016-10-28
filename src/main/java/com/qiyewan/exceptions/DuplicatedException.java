package com.qiyewan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by lhzbxx on 2016/10/27.
 *
 * 重复
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicatedException extends RuntimeException {

    public DuplicatedException(String message) {
        super(message);
    }

}
