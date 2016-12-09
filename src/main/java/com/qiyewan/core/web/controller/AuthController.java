package com.qiyewan.core.web.controller;

import com.qiyewan.common.exceptions.InvalidRequestException;
import com.qiyewan.core.domain.LoginHistory;
import com.qiyewan.core.domain.User;
import com.qiyewan.core.dto.PhonePayload;
import com.qiyewan.core.dto.ResultDto;
import com.qiyewan.core.dto.TokenDto;
import com.qiyewan.core.dto.UserStatusDto;
import com.qiyewan.core.service.CaptchaService;
import com.qiyewan.core.service.LoginHistoryService;
import com.qiyewan.core.service.TokenService;
import com.qiyewan.core.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 用户-身份认证
 */
@RestController
public class AuthController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private LoginHistoryService loginHistoryService;

    @CrossOrigin
    @PostMapping("/captcha.do")
    public ResultDto captcha(@RequestParam String phone) {
        PhonePayload phonePayload = captchaService.setCaptcha(phone);
        rabbitTemplate.convertAndSend("sms-queue", phonePayload);
        return new ResultDto();
    }

    @CrossOrigin
    @GetMapping(value = "/check-phone.do")
    public UserStatusDto checkPhone(@RequestParam String phone) {
        return new UserStatusDto(userService.isRegistered(phone));
    }

    @CrossOrigin
    @GetMapping(value = "/auth")
    public TokenDto login(@RequestParam String phone,
                          @RequestParam String password,
                          @RequestParam String mode) {
        User user = userService.getUserByAuth(new PhonePayload(phone, password));
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        String token = tokenService.setToken(user.getId());
        rabbitTemplate.convertAndSend("login-history-record-queue",
                loginHistoryService.record(
                        new LoginHistory(user.getId(), ip, token, mode)));
        return new TokenDto(token);
    }

    @CrossOrigin
    @PostMapping(value = "/auth")
    public TokenDto register(@Validated @RequestBody PhonePayload phonePayload) {
        PhonePayload auth = captchaService.getAuthDtoWithPhone(phonePayload.getPhone());
        if (!auth.isEqual(phonePayload)) {
            throw new InvalidRequestException("验证码不正确。");
        }
        Long userId = userService.createAndSaveUser(phonePayload);
        String token = tokenService.setToken(userId);
        return new TokenDto(token);
    }

    // 修改密码
    @CrossOrigin
    @PatchMapping("/auth")
    public TokenDto resetPassword(@Validated @RequestBody PhonePayload phonePayload) {
        PhonePayload auth = captchaService.getAuthDtoWithPhone(phonePayload.getPhone());
        if (!auth.isEqual(phonePayload)) {
            throw new InvalidRequestException("验证码不正确。");
        }
        Long userId = userService.updateUserPassword(phonePayload);
        String token = tokenService.setToken(userId);
        return new TokenDto(token);
    }
}
