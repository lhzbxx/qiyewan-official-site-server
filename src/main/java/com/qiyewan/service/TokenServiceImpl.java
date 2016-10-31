package com.qiyewan.service;

import com.qiyewan.exceptions.NoAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
        String userId = template.opsForValue().get(keyWithToken(token));
        if (userId == null) throw new NoAuthException("Error.Auth.INVALID_TOKEN");
        return Long.parseLong(userId);
    }

    @Override
    public String setToken(Long userId) {
        String token = generateToken();
        String key = keyWithToken(token);
        template.opsForValue().set(key, userId.toString());
        template.expire(key, expire, TimeUnit.DAYS);
        return token;
    }

    private String keyWithToken(String token) {
        return "token:" + token;
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }

}
