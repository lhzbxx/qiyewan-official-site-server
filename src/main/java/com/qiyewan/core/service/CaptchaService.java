package com.qiyewan.core.service;

import com.qiyewan.core.other.payload.PhonePayload;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 手机验证码-管理
 */
public interface CaptchaService {
    // 6位
    // 长度
    int captchaLength = 6;
    // 15分钟
    // 过期时间
    long expire = 15 * 60;
    String numberChars = "0123456789";

    PhonePayload getAuthDtoWithPhone(String phone);

    PhonePayload setCaptcha(String phone);
}
