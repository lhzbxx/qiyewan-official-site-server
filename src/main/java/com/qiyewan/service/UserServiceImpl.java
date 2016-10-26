package com.qiyewan.service;

import com.qiyewan.domain.User;
import com.qiyewan.domain.UserAuth;
import com.qiyewan.dto.AuthDto;
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

    @Override
    public User getUserById(Long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public User getUserByAuth(AuthDto authDto) {
        UserAuth userAuth = this.userAuthRepository.
                findFirstByIdentifierAndCredential(authDto.getPhone(), authDto.getPassword());
        if (userAuth == null) {
            // TODO: 2016/10/26 Throw IllegalActionException
        }
        Long userId = userAuth.getUserId();
        return this.userRepository.findOne(userId);
    }

    @Override
    public Long createAndSaveUser(AuthDto authDto) {
        if (userAuthRepository.findFirstByIdentifier(authDto.getPhone()) != null) {
//            throw new DuplicatedException("已经被绑定或注册。");
        }
        User user = new User();
        userRepository.saveAndFlush(user);
        UserAuth userAuth = new UserAuth(user.getId(), authDto.getPhone(), authDto.getPassword());
        this.userAuthRepository.save(userAuth);
        return user.getId();
    }

    @Override
    public Long updateUserPhone(AuthDto authDto) {
        return null;
    }
}
