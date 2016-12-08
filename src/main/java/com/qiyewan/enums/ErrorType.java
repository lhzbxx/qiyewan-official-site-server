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
    DATABASE_ERROR("ERROR.DATABASE"),
    UNKNOWN_ERROR("ERROR.UNKNOWN"),
    /**
     * 正常使用下，面向用户的错误。
     * ↓↓↓↓↓↓
     */
    // 缺少权限
    NO_AUTH_ERROR("ERROR.NO_AUTH"),
    // 资源已存在
    EXISTED_ERROR("ERROR.EXISTED"),
    // 资源不存在
    NOT_FOUND_ERROR("ERROR.NOT_FOUND"),
    // 请求错误
    INVALID_REQUEST_ERROR("ERROR.INVALID_REQUEST"),
    // 参数非法
    INVALID_PARAM_ERROR("ERROR.INVALID_PARAM"),
    /**
     * 前端对接API时的错误。
     * ↓↓↓↓↓↓
     */
    // 不存在的API
    INVALID_API_ERROR("ERROR.INVALID_API"),
    // 请求格式不合法（非JSON）
    UNSUPPORTED_MEDIA("ERROR.UNSUPPORTED_MEDIA"),
    // 请求方法错误（GET/POST/PATCH/PUT/DELETE）
    INVALID_METHOD_ERROR("ERROR.INVALID_REQUEST_METHOD");
    private final String code;

    ErrorType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
