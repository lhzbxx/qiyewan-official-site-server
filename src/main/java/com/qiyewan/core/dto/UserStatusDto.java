package com.qiyewan.core.dto;

import lombok.Data;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 用户状态的格式
 */
@Data
public class UserStatusDto {
    // 是否已注册
    private boolean isRegistered;

    public UserStatusDto() {}

    public UserStatusDto(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }
}
