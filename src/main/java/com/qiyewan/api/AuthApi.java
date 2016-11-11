package com.qiyewan.api;

import com.qiyewan.domain.LoginHistory;
import com.qiyewan.domain.User;
import com.qiyewan.dto.AuthDto;
import com.qiyewan.dto.ErrorDto;
import com.qiyewan.dto.UserDto;
import com.qiyewan.exceptions.IllegalActionException;
import com.qiyewan.exceptions.InvalidParamException;
import com.qiyewan.service.CaptchaService;
import com.qiyewan.service.LoginHistoryService;
import com.qiyewan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 用户相关
 */

@RestController
public class AuthApi {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginHistoryService loginHistoryService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping("/login-history")
    public Page<LoginHistory> showLoginHistory(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Long userId = (Long) request.getAttribute("userId");
        return loginHistoryService.show(userId, pageable);
    }

    // 重新绑定手机
    @CrossOrigin
    @PutMapping("/auth")
    public ErrorDto<?> resetPhone(@Validated @RequestBody AuthDto authDto) {
        Long userId = (Long) request.getAttribute("userId");
        if (authDto.getCaptcha().isEmpty()) {
            throw new InvalidParamException("Error.Param.NO_CAPTCHA");
        }
        AuthDto auth = captchaService.getAuthDtoWithPhone(authDto.getPhone());
        if (!auth.isEqual(authDto)) {
            throw new IllegalActionException("Error.Action.WRONG_CAPTCHA");
        }
        userService.updateUserPhone(userId, authDto);
        return new ErrorDto<>();
    }

    @CrossOrigin
    @GetMapping("/users")
    public User showInfo() {
        Long userId = (Long) request.getAttribute("userId");
        return userService.getUserById(userId);
    }

    // 修改头像或昵称
    @CrossOrigin
    @PatchMapping("/users")
    public ErrorDto<?> resetInfo(@Validated @RequestBody UserDto userDto) {
        Long userId = (Long) request.getAttribute("userId");
        userService.updateUserInfo(userId, userDto);
        return new ErrorDto<>();
    }

}
