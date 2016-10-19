package com.qiyewan.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

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

    private String phone;

    private String avatar;

    private Date createAt;

    private Date updateAt;

}
