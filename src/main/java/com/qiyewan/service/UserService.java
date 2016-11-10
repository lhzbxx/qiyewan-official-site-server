package com.qiyewan.service;

import com.qiyewan.domain.User;
import com.qiyewan.dto.AuthDto;
import com.qiyewan.dto.UserDto;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 用户和用户认证
 */

public interface UserService {

    User getUserById(Long id);

    User getUserByAuth(AuthDto authDto);

    Long createAndSaveUser(AuthDto authDto);

    Long updateUserPhone(Long userId, AuthDto authDto);

    Long updateUserPassword(AuthDto authDto);

    User updateUserInfo(Long userId, UserDto userDto);

}
