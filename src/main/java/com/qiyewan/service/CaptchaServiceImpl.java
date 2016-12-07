package com.qiyewan.service;

import com.qiyewan.dto.AuthDto;
import com.qiyewan.exceptions.InvalidRequestException;
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
    private RedisTemplate<String, AuthDto> template;

    @Override
    public AuthDto getAuthDtoWithPhone(String phone) {
        String key = keyWithPhone(phone);
        AuthDto authDto = template.opsForValue().get(key);
        if (authDto == null) {
            throw new InvalidRequestException("Error.Action.INVALID_OR_EXPIRED_CAPTCHA");
        }
        return authDto;
    }

    @Override
    public AuthDto setCaptcha(String phone) {
        String captcha = generateCaptcha();
        String key = keyWithPhone(phone);
        AuthDto authDto = new AuthDto(phone, "", captcha);
        template.opsForValue().set(key, authDto);
        template.expire(key, expire, TimeUnit.SECONDS);
        return authDto;
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
