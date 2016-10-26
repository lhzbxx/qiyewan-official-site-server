package com.qiyewan.dto;

import com.qiyewan.enums.ErrorType;
import lombok.Data;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 用户请求的响应结果，异常/正常。
 */

@Data
public class ErrorDto<T> {

    private Integer error = 0;
    private String message = "请求成功！";
    private T detail = null;

    public ErrorDto() {}

    public ErrorDto(ErrorType errorType) {
        this.error = errorType.getCode();
        this.message = errorType.getReason();
    }

    public ErrorDto(ErrorType errorType, T detail) {
        this.error = errorType.getCode();
        this.message = errorType.getReason();
        this.detail = detail;
    }

    public ErrorDto(T detail) {
        this.detail = detail;
    }

    public ErrorDto(Integer error, String message) {
        this.error = error;
        this.message = message;
    }

    public ErrorDto(Integer error, String message, T detail) {
        this.error = error;
        this.message = message;
        this.detail = detail;
    }

}
