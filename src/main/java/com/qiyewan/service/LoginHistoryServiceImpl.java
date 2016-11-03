package com.qiyewan.service;

import com.qiyewan.domain.LoginHistory;
import com.qiyewan.repository.LoginHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lhzbxx on 2016/10/28.
 *
 * 用户-登录记录
 */

@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {

    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    @Override
    public void record(Long userId, String ip, String region) {
        loginHistoryRepository.save(new LoginHistory(userId, ip, region));
    }
}
