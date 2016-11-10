package com.qiyewan.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qiyewan.dto.UserDto;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 用户
 */

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    // 手机号
    private String phone;

    // 头像
    private String avatar;

    // 会员名
    private String nickname = "企业湾-会员";

    // UUID
    private String uuid = UUID.randomUUID().toString();

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateAt = new Date();

    public User() {}

    public User(String phone) {
        this.phone = phone;
    }

    public void resetInfo(UserDto userDto) {
        this.avatar = userDto.getAvatar();
        this.nickname = userDto.getNickname();
    }

}
