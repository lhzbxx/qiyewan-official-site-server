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

    @NotNull
    @Size
    private String phone;

    @NotNull
    @Size
    private String password;

    @Size(min = 4, max = 6)
    private String captcha;

    private Date date = new Date();

    public AuthDto() {}

    public AuthDto(String phone, String password, String captcha) {
        this.phone = phone;
        this.password = password;
        this.captcha = captcha;
    }

}
