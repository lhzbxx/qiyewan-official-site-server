package com.qiyewan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 登录-历史记录
 */

@Entity
@Data
public class LoginHistory {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @JsonIgnore
    private Long userId;

    // IP
    private String ip;

    // 登录地点
    private String address;

    @JsonIgnore
    private String token;

    // 登录时间
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();

    public LoginHistory() {}

    public LoginHistory(Long userId, String ip, String address, String token) {
        this.userId = userId;
        this.ip = ip;
        this.address = address;
        this.token = token;
    }

}
