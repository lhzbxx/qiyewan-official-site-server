package com.qiyewan.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 用户-认证
 */
public class UserAuth {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String identifier;

    private String credential;

    private String salt;

}
