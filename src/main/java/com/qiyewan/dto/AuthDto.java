package com.qiyewan.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 身份认证的格式
 */

@Data
public class AuthDto {
    @NotNull(message = "手机号不能为空。")
    @Size
    private String phone;

    @NotNull(message = "密码不能为空。")
    @Size
    private String password;

    @NotNull(message = "验证码不能为空。")
    @Size(min = 6, max = 6, message = "验证码格式不正确。")
    private String captcha;

    private Date date = new Date();

    public AuthDto() {}

    public AuthDto(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public AuthDto(String phone, String password, String captcha) {
        this.phone = phone;
        this.password = password;
        this.captcha = captcha;
    }

    public boolean isEqual(AuthDto authDto) {
        return this.phone.equals(authDto.getPhone()) &&
                this.captcha.equals(authDto.getCaptcha());
    }
}
