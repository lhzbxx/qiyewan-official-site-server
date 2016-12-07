package com.qiyewan.service;

import com.qiyewan.domain.User;
import com.qiyewan.dto.PhonePayload;
import com.qiyewan.dto.UserDto;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 用户和用户认证
 */
public interface UserService {
    boolean isRegistered(String phone);

    User getUserById(Long id);

    User getUserByAuth(PhonePayload phonePayload);

    Long createAndSaveUser(PhonePayload phonePayload);

    Long updateUserPhone(Long userId, PhonePayload phonePayload);

    Long updateUserPassword(PhonePayload phonePayload);

    User updateUserInfo(Long userId, UserDto userDto);
}
