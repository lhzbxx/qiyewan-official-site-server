package com.qiyewan.enums;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 统一的错误定义
 */
public enum ErrorType {
    /**
     * 测试环境下的错误。
     * ↓↓↓↓↓↓
     */
    // 数据库读写失败（测试环境）
    DatabaseError("ERROR.DATABASE"),
    UnknownError("ERROR.UNKNOWN"),
    /**
     * 正常使用下，面向用户的错误。
     * ↓↓↓↓↓↓
     */
    // 缺少权限
    NoAuthError("ERROR.NO_AUTH"),
    // 资源已存在
    ExistedError("ERROR.EXISTED"),
    // 资源不存在
    NotFoundError("ERROR.NOT_FOUND"),
    // 请求错误
    InvalidRequestError("ERROR.INVALID_REQUEST"),
    // 参数非法
    InvalidParamError("ERROR.INVALID_PARAM"),
    /**
     * 前端对接API时的错误。
     * ↓↓↓↓↓↓
     */
    // 不存在的API
    InvalidApiError("ERROR.INVALID_API"),
    // 请求格式不合法（非JSON）
    UnsupportedMedia("ERROR.UNSUPPORTED_MEDIA"),
    // 请求方法错误（GET/POST/PATCH/PUT/DELETE）
    InvalidMethodError("ERROR.INVALID_REQUEST_METHOD");
    private final String code;

    ErrorType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
