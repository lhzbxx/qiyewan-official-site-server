package com.qiyewan.service;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 用户-登录记录
 */

public interface LoginHistoryService {

    void record(Long userId, String ip, String region);

}
