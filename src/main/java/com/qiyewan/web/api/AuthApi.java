package com.qiyewan.web.api;

import com.qiyewan.domain.LoginHistory;
import com.qiyewan.domain.User;
import com.qiyewan.dto.PhonePayload;
import com.qiyewan.dto.ResultDto;
import com.qiyewan.dto.UserInfoDto;
import com.qiyewan.exceptions.InvalidRequestException;
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
    public ResultDto resetPhone(@Validated @RequestBody PhonePayload phonePayload) {
        Long userId = (Long) request.getAttribute("userId");
        PhonePayload auth = captchaService.getAuthDtoWithPhone(phonePayload.getPhone());
        if (!auth.isEqual(phonePayload)) {
            throw new InvalidRequestException("验证码错误。");
        }
        userService.updateUserPhone(userId, phonePayload);
        return new ResultDto();
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
    public ResultDto resetInfo(@Validated @RequestBody UserInfoDto userInfoDto) {
        Long userId = (Long) request.getAttribute("userId");
        userService.updateUserInfo(userId, userInfoDto);
        return new ResultDto();
    }
}