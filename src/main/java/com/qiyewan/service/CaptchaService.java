package com.qiyewan.service;

import com.qiyewan.dto.AuthDto;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 手机验证码-管理
 */

public interface CaptchaService {

    int captchaLength = 6;

    String numberChars = "0123456789";

    AuthDto getUserWithToken(String token);

    void setCaptcha(String phone, String password);

}
