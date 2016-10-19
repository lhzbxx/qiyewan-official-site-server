package com.qiyewan.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private Long id;

    private Long userId;

    // IP
    private String ip;

    // 登录地点
    private String address;

    // 登录时间
    private Date createAt;

}
