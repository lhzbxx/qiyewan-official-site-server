package com.qiyewan.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiyewan.dto.AuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 手机验证码-管理
 */

@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private StringRedisTemplate template;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public AuthDto getUserWithToken(String token) {
        String key = keyWithToken(token);
        try {
            return mapper.readValue(template.opsForValue().get(key), AuthDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setCaptcha(String phone, String password) {
        String token = generateToken();
        try {
            template.opsForValue().set(keyWithToken(token), mapper.writeValueAsString(new AuthDto(phone, password, token)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private String keyWithToken(String token) {
        return "captcha:" + token;
    }

    private String generateToken() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < captchaLength; i++)
            stringBuilder.append(numberChars.charAt(random.nextInt(numberChars.length())));
        return stringBuilder.toString();
    }
}
