package com.qiyewan.enums;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 统一的错误定义
 */

public enum ErrorType {

    // 系统级别的错误
    InvalidMethodError(1001, "请求方法不正确。"),
    DatabaseError(1002, "数据库写入失败。"),
    InvalidApiError(1003, "请求的API不存在。"),
    UnsupportedMedia(1004, "请求格式不合法，请使用JSON！"),

    // 用户行为错误
    NoAuthError(2001, "缺少权限。"),
    NotActivatedError(2002, "账号未激活。"),
    InvalidUserError(2003, "账号已被封停。"),

    // 不合法的操作或请求
    InvalidParamError(3001, "参数错误。"),
    LackParamError(3002, "缺少参数。"),
    DuplicatedError(3003, "资源已存在。"),
    NotFoundError(3004, "不存在的资源。"),
    IllegalActionError(3005, "非法的请求。");

    private final int code;
    private final String reason;

    private ErrorType(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public int getCode() {
        return this.code;
    }

    public String getReason() {
        return this.reason;
    }

    public String toString(String detail) {
        return this.reason + detail;
    }

}
