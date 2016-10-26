package com.qiyewan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 认证的令牌-管理
 */

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private StringRedisTemplate template;

    @Override
    public Long getUserIdWithToken(String token) {
        return Long.parseLong(template.opsForValue().get(keyWithToken(token)));
    }

    @Override
    public String setToken(Long userId) {
        String token = generateToken();
        template.opsForValue().set(keyWithToken(token), userId.toString());
        return token;
    }

    private String keyWithToken(String token) {
        return "token:" + token;
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }

}
