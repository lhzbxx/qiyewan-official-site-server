package com.qiyewan.core.service;

import com.qiyewan.core.domain.LoginHistory;
import com.qiyewan.core.domain.LoginHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<LoginHistory> show(Long userId, Pageable pageable) {
        return loginHistoryRepository.findByUserId(userId, pageable);
    }

    @Override
    public Long record(LoginHistory loginHistory) {
        loginHistoryRepository.saveAndFlush(loginHistory);
        return loginHistory.getId();
    }
}
