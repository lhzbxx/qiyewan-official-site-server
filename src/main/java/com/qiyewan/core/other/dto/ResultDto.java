package com.qiyewan.core.other.dto;

import com.qiyewan.common.enums.ErrorType;
import lombok.Data;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 用户请求产生异常的响应结果。
 */

@Data
public class ResultDto {
    private String type = "SUCCESS";
    private String message = "成功。";

    public ResultDto() {}

    public ResultDto(ErrorType errorType, String message) {
        this.type = errorType.getCode();
        this.message = message;
    }
}
