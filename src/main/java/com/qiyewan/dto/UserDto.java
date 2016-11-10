package com.qiyewan.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 用户信息的格式
 */

@Data
public class UserDto {

    @NotNull
    private String nickname;

    @NotNull
    private String avatar;

    private Date date = new Date();

    public UserDto() {}


}
