package com.qiyewan.core.service;

import com.qiyewan.core.domain.LoginHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 用户-登录记录
 */
public interface LoginHistoryService {
    Page<LoginHistory> show(Long userId, Pageable pageable);

    Long record(LoginHistory loginHistory);
}
