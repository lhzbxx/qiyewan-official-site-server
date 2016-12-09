package com.qiyewan.core.service;

import com.qiyewan.core.domain.*;
import com.qiyewan.core.dto.PhonePayload;
import com.qiyewan.core.dto.UserInfoDto;
import com.qiyewan.common.exceptions.ExistedException;
import com.qiyewan.common.exceptions.InvalidParamException;
import com.qiyewan.common.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 用户和用户认证
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public boolean isRegistered(String phone) {
        return userAuthRepository.findFirstByIdentifier(phone) != null;
    }

    @Override
    public User getUserById(Long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public User getUserByAuth(PhonePayload phonePayload) {
        UserAuth userAuth = this.userAuthRepository.findFirstByIdentifier(phonePayload.getPhone());
        checkUserAuth(userAuth);
        if (!userAuth.isValid(phonePayload.getPassword())) {
            throw new InvalidParamException("密码错误。");
        }
        Long userId = userAuth.getUserId();
        return this.userRepository.findOne(userId);
    }

    @Override
    @Transactional
    public Long createAndSaveUser(PhonePayload phonePayload) {
        if (userAuthRepository.findFirstByIdentifier(phonePayload.getPhone()) != null) {
            throw new ExistedException("用户已存在。");
        }
        User user = new User(phonePayload.getPhone());
        userRepository.saveAndFlush(user);
        UserAuth userAuth = new UserAuth(user.getId(), phonePayload.getPhone(), phonePayload.getPassword());
        this.userAuthRepository.save(userAuth);
        this.companyRepository.save(new Company(user.getId()));
        return user.getId();
    }

    @Override
    public Long updateUserPhone(Long userId, PhonePayload phonePayload) {
        UserAuth userAuth = userAuthRepository.findByUserId(userId);
        checkUserAuth(userAuth);
        User user = userRepository.findOne(userId);
        user.setPhone(phonePayload.getPhone());
        userAuth.setIdentifier(phonePayload.getPhone());
        userAuth.resetCredential(phonePayload.getPassword());
        userRepository.save(user);
        userAuthRepository.save(userAuth);
        return userId;
    }

    @Override
    public Long updateUserPassword(PhonePayload phonePayload) {
        UserAuth userAuth = userAuthRepository.findFirstByIdentifier(phonePayload.getPhone());
        checkUserAuth(userAuth);
        userAuth.resetCredential(phonePayload.getPassword());
        userAuthRepository.save(userAuth);
        return userAuth.getUserId();
    }

    @Override
    public User updateUserInfo(Long userId, UserInfoDto userInfoDto) {
        User user = userRepository.findOne(userId);
        user.reset(userInfoDto);
        userRepository.save(user);
        return user;
    }

    private void checkUserAuth(UserAuth userAuth) {
        if (userAuth == null) {
            throw new NotFoundException("用户不存在。");
        }
    }
}
