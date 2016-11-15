package com.qiyewan.domain;

import com.qiyewan.utils.Crypto.Md5Util;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 用户-认证
 */

@Entity
@Data
public class UserAuth {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private Long userId;

    private String phone;

    private String password;

    private String salt;

    public UserAuth() {}

    public UserAuth(Long userId, String phone, String password) {
        this.salt = UUID.randomUUID().toString();
        this.userId = userId;
        this.phone = phone;
        this.password = Md5Util.genMd5(password);
    }

    public void resetPassword(String password) {
        this.password = Md5Util.genMd5(password);
    }

    public boolean isValid(String password) {
        return this.password.equals(Md5Util.genMd5(password));
    }

}
