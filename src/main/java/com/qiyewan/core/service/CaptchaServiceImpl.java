package com.qiyewan.core.service;

import com.qiyewan.core.dto.PhonePayload;
import com.qiyewan.common.exceptions.InvalidParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 手机验证码-管理
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {
    @Autowired
    private RedisTemplate<String, PhonePayload> template;

    @Override
    public PhonePayload getAuthDtoWithPhone(String phone) {
        String key = keyWithPhone(phone);
        PhonePayload phonePayload = template.opsForValue().get(key);
        if (phonePayload == null) {
            throw new InvalidParamException("无效的验证码。");
        }
        return phonePayload;
    }

    @Override
    public PhonePayload setCaptcha(String phone) {
        String captcha = generateCaptcha();
        String key = keyWithPhone(phone);
        PhonePayload phonePayload = new PhonePayload(phone, "", captcha);
        template.opsForValue().set(key, phonePayload);
        template.expire(key, expire, TimeUnit.SECONDS);
        return phonePayload;
    }

    private String keyWithPhone(String phone) {
        return "captcha:" + phone;
    }

    private String generateCaptcha() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < captchaLength; i++)
            stringBuilder.append(numberChars.charAt(random.nextInt(numberChars.length())));
        return stringBuilder.toString();
    }
}
