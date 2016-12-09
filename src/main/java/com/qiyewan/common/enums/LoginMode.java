package com.qiyewan.common.enums;

import com.qiyewan.common.exceptions.InvalidParamException;

/**
 * Created by lhzbxx on 2016/11/8.
 *
 * 登录方式
 */
public enum LoginMode {
    // PC
    WEB_PC,
    // 移动端
    WEB_MOBILE;

    public static LoginMode convert(String s) {
        try {
            return LoginMode.valueOf(s);
        } catch (Exception e) {
            throw new InvalidParamException("不合法的`LoginMode`参数。");
        }
    }
}
