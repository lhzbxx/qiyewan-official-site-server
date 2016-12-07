package com.qiyewan.service;

import com.qiyewan.domain.Company;
import com.qiyewan.domain.User;
import com.qiyewan.domain.UserAuth;
import com.qiyewan.dto.AuthDto;
import com.qiyewan.dto.UserDto;
import com.qiyewan.exceptions.ExistedException;
import com.qiyewan.exceptions.NoAuthException;
import com.qiyewan.domain.CompanyRepository;
import com.qiyewan.domain.UserAuthRepository;
import com.qiyewan.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User getUserByAuth(AuthDto authDto) {
        UserAuth userAuth = this.userAuthRepository.findFirstByIdentifier(authDto.getPhone());
        if (userAuth == null) {
            throw new NoAuthException("Error.Auth.USER_NOT_EXISTS");
        }
        if (!userAuth.isValid(authDto.getPassword())) {
            throw new NoAuthException("Error.Auth.WRONG_PASSWORD");
        }
        Long userId = userAuth.getUserId();
        return this.userRepository.findOne(userId);
    }

    @Override
    public Long createAndSaveUser(AuthDto authDto) {
        if (userAuthRepository.findFirstByIdentifier(authDto.getPhone()) != null) {
            throw new ExistedException("Error.Duplicated.USER_EXISTS");
        }
        User user = new User(authDto.getPhone());
        userRepository.saveAndFlush(user);
        UserAuth userAuth = new UserAuth(user.getId(), authDto.getPhone(), authDto.getPassword());
        this.userAuthRepository.save(userAuth);
        this.companyRepository.save(new Company(user.getId()));
        return user.getId();
    }

    @Override
    public Long updateUserPhone(Long userId, AuthDto authDto) {
        UserAuth userAuth = userAuthRepository.findByUserId(userId);
        if (userAuth == null) {
            throw new NoAuthException("Error.Auth.USER_NOT_EXISTS");
        }
        User user = userRepository.findOne(userId);
        user.setPhone(authDto.getPhone());
        userAuth.setIdentifier(authDto.getPhone());
        userAuth.resetCredential(authDto.getPassword());
        userRepository.save(user);
        userAuthRepository.save(userAuth);
        return userId;
    }

    @Override
    public Long updateUserPassword(AuthDto authDto) {
        UserAuth userAuth = userAuthRepository.findFirstByIdentifier(authDto.getPhone());
        if (userAuth == null) {
            throw new NoAuthException("Error.Auth.USER_NOT_EXISTS");
        }
        userAuth.resetCredential(authDto.getPassword());
        userAuthRepository.save(userAuth);
        return userAuth.getUserId();
    }

    @Override
    public User updateUserInfo(Long userId, UserDto userDto) {
        User user = userRepository.findOne(userId);
        user.reset(userDto);
        userRepository.save(user);
        return user;
    }
}
