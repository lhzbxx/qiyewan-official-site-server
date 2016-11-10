package com.qiyewan.controller;

import com.qiyewan.domain.LoginHistory;
import com.qiyewan.domain.User;
import com.qiyewan.dto.AuthDto;
import com.qiyewan.dto.ErrorDto;
import com.qiyewan.exceptions.IllegalActionException;
import com.qiyewan.exceptions.InvalidParamException;
import com.qiyewan.service.CaptchaService;
import com.qiyewan.service.LoginHistoryService;
import com.qiyewan.service.TokenService;
import com.qiyewan.service.UserService;
import com.qiyewan.utils.Ip2Region.Ip2RegionUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lhzbxx on 2016/10/20.
 * <p>
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
    @GetMapping(value = "/auth")
    public ErrorDto<?> login(@RequestParam String phone,
                             @RequestParam String password,
                             @RequestParam(defaultValue = "PC") String mode) {
        User user = userService.getUserByAuth(new AuthDto(phone, password));
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        String token = tokenService.setToken(user.getId());
        rabbitTemplate.convertAndSend("login-history-record-queue",
                loginHistoryService.record(new LoginHistory(user.getId(), ip, token, mode)));
        return new ErrorDto<>(token);
    }

    @CrossOrigin
    @PostMapping(value = "/auth")
    public ErrorDto<?> register(@Validated @RequestBody AuthDto authDto) {
        if (authDto.getCaptcha().isEmpty()) {
            throw new InvalidParamException("Error.Param.NO_CAPTCHA");
        }
        AuthDto auth = captchaService.getAuthDtoWithPhone(authDto.getPhone());
        if (!auth.isEqual(authDto)) {
            throw new IllegalActionException("Error.Action.WRONG_CAPTCHA");
        }
        Long userId = userService.createAndSaveUser(authDto);
        String token = tokenService.setToken(userId);
        return new ErrorDto<>(token);
    }

    // 修改密码
    @CrossOrigin
    @PatchMapping("/auth")
    public ErrorDto<?> resetPassword(@Validated @RequestBody AuthDto authDto) {
        if (authDto.getCaptcha().isEmpty()) {
            throw new InvalidParamException("Error.Param.NO_CAPTCHA");
        }
        AuthDto auth = captchaService.getAuthDtoWithPhone(authDto.getPhone());
        if (!auth.isEqual(authDto)) {
            throw new IllegalActionException("Error.Action.WRONG_CAPTCHA");
        }
        userService.updateUserPassword(auth);
        return new ErrorDto<>();
    }

    @CrossOrigin
    @RequestMapping(value = "/captcha/{phone}", method = RequestMethod.POST)
    public ErrorDto<?> requestCaptcha(@PathVariable String phone) {
        AuthDto authDto = captchaService.setCaptcha(phone);
        rabbitTemplate.convertAndSend("sms-queue", authDto);
        return new ErrorDto<>();
    }

    @CrossOrigin
    @GetMapping(value = "/region")
    public String getRegion() {
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        return new Ip2RegionUtil(ip).toRegionCode();
    }

}
