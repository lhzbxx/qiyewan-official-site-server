package com.qiyewan.service;

import com.qiyewan.domain.LoginHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 用户-登录记录
 */

public interface LoginHistoryService {

    Page<LoginHistory> show(Long userId, Pageable pageable);

    void record(LoginHistory loginHistory);

}
