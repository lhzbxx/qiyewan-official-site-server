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
    private String type = "SUCCESS";
    private String message = "成功。";

    public ErrorDto() {}

    public ErrorDto(ErrorType errorType) {
        this.type = errorType.getCode();
    }

    public ErrorDto(ErrorType errorType, String message) {
        this.type = errorType.getCode();
        this.message = message;
    }
}
