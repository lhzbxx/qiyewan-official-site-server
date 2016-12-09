package com.qiyewan.core.other.dto;

import com.qiyewan.core.service.TokenService;
import lombok.Data;

import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 令牌
 */
@Data
public class TokenDto {
    private String token;
    private Date createAt;
    private Date expire;

    public TokenDto() {}

    public TokenDto(String token) {
        this.token = token;
        this.createAt = new Date();
        this.expire = new Date(this.createAt.getTime() + TokenService.expire * 86400000);
    }
}
