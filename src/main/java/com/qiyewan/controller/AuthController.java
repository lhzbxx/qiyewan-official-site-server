package com.qiyewan.controller;

import com.qiyewan.dto.AuthDto;
import com.qiyewan.dto.ErrorDto;
import com.qiyewan.enums.ErrorType;
import com.qiyewan.service.CaptchaService;
import com.qiyewan.service.TokenService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 用户-身份认证
 */

@RestController
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @Autowired
//    private UserService userService;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ErrorDto<?> register(@Validated @RequestBody AuthDto authDto) {
        if (authDto.getCaptcha().isEmpty()) {
            return new ErrorDto<>(ErrorType.InvalidParamError, "验证码不能为空！");
        }
        return new ErrorDto<>();
    }

    @RequestMapping(value = "/captcha/{phone}", method = RequestMethod.POST)
    public ErrorDto<?> requestCaptcha(@PathVariable String phone) {
        AuthDto authDto = captchaService.setCaptcha(phone);
        rabbitTemplate.convertAndSend("sms-queue", authDto);
        return new ErrorDto<>();
    }

}
