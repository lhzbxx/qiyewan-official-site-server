package com.qiyewan.core.other.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 用户信息的格式
 */
@Data
public class UserInfoPayload {
    @NotNull(message = "昵称不能为空。")
    private String nickname;
    @NotNull(message = "头像不能为空。")
    private String avatar;
    private Date date = new Date();

    public UserInfoPayload() {}
}
