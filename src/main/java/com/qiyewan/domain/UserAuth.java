package com.qiyewan.domain;

import com.qiyewan.enums.AuthType;
import com.qiyewan.utils.Crypto.Md5Util;
import lombok.Data;

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
    private Long userId;
    // 标识符
    private String identifier;
    // 认证码
    private String credential;
    // 类型
    private AuthType origin = AuthType.Phone;
    // 加密盐
    private String salt;

    public UserAuth() {}

    public UserAuth(Long userId, String identifier, String credential) {
        this.salt = UUID.randomUUID().toString();
        this.userId = userId;
        this.identifier = identifier;
        this.credential = Md5Util.genMd5(credential);
    }

    public void resetPassword(String password) {
        this.credential = Md5Util.genMd5(password);
    }

    public boolean isValid(String password) {
        return this.credential.equals(Md5Util.genMd5(password));
    }

}
