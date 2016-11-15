package com.qiyewan.service;

import com.qiyewan.domain.Company;
import com.qiyewan.domain.User;
import com.qiyewan.domain.UserAuth;
import com.qiyewan.dto.AuthDto;
import com.qiyewan.dto.UserDto;
import com.qiyewan.exceptions.DuplicatedException;
import com.qiyewan.exceptions.NoAuthException;
import com.qiyewan.repository.CompanyRepository;
import com.qiyewan.repository.UserAuthRepository;
import com.qiyewan.repository.UserRepository;
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
    public User getUserById(Long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public User getUserByAuth(AuthDto authDto) {
        UserAuth userAuth = this.userAuthRepository.findFirstByPhone(authDto.getPhone());
        if (userAuth == null) {
            throw new NoAuthException("Error.Auth.USER_NOT_EXISTS");
        }
        if ( ! userAuth.isValid(authDto.getPassword())) {
            throw new NoAuthException("Error.Auth.WRONG_PASSWORD");
        }
        Long userId = userAuth.getUserId();
        return this.userRepository.findOne(userId);
    }

    @Override
    public Long createAndSaveUser(AuthDto authDto) {
        if (userAuthRepository.findFirstByPhone(authDto.getPhone()) != null) {
            throw new DuplicatedException("Error.Duplicated.USER_EXISTS");
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
        userAuth.setPhone(authDto.getPhone());
        userAuth.setPassword(authDto.getPassword());
        userRepository.save(user);
        userAuthRepository.save(userAuth);
        return userId;
    }

    @Override
    public Long updateUserPassword(AuthDto authDto) {
        UserAuth userAuth = userAuthRepository.findFirstByPhone(authDto.getPhone());
        if (userAuth == null) {
            throw new NoAuthException("Error.Auth.USER_NOT_EXISTS");
        }
        userAuth.setPassword(authDto.getPassword());
        userAuthRepository.save(userAuth);
        return userAuth.getUserId();
    }

    @Override
    public User updateUserInfo(Long userId, UserDto userDto) {
        User user = userRepository.findOne(userId);
        user.resetInfo(userDto);
        userRepository.save(user);
        return user;
    }
}
