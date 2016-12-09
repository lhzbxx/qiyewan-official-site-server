package com.qiyewan.core.service;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 认证的令牌-管理
 */
public interface TokenService {
    // 64位
    // 长度
    int tokenLength = 64;
    // 15天
    // 过期时间
    long expire = 15;

    Long getUserIdWithToken(String token);

    String setToken(Long userId);
}
