package com.qiyewan.dto;

import com.qiyewan.enums.ErrorType;
import lombok.Data;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 用户请求产生异常的响应结果。
 */

@Data
public class ErrorDto {
    private Integer error = 0;
    private String message = "请求成功！";
    private String detail = null;

    public ErrorDto() {}

    public ErrorDto(ErrorType errorType) {
        this.error = errorType.getCode();
        this.message = errorType.getReason();
    }

    public ErrorDto(ErrorType errorType, String detail) {
        this.error = errorType.getCode();
        this.message = errorType.getReason();
        this.detail = detail;
    }

    public ErrorDto(String detail) {
        this.detail = detail;
    }

    public ErrorDto(Integer error, java.lang.String message) {
        this.error = error;
        this.message = message;
    }

    public ErrorDto(Integer error, java.lang.String message, String detail) {
        this.error = error;
        this.message = message;
        this.detail = detail;
    }

}
