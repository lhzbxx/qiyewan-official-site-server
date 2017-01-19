package com.qiyewan.core.domain;

import com.qiyewan.common.enums.AuthType;
import com.qiyewan.common.utils.Md5Util;
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
    private Long userId;
    // 标识符
    @Column(unique = true, length = 128)
    private String identifier;
    // 认证码
    private String credential;
    // 类型
    private AuthType origin = AuthType.PHONE;
    // 加密盐
    private String salt;

    public UserAuth() {}

    public UserAuth(Long userId, String identifier, String credential) {
        this.salt = UUID.randomUUID().toString();
        this.userId = userId;
        this.identifier = identifier;
        this.credential = Md5Util.genMd5(credential);
    }

    public UserAuth(Long userId, String identifier, String credential, AuthType origin) {
        this.salt = UUID.randomUUID().toString();
        this.userId = userId;
        this.identifier = identifier;
        this.credential = Md5Util.genMd5(credential);
        this.origin = origin;
    }

    public void resetCredential(String credential) {
        this.credential = Md5Util.genMd5(credential);
    }

    public boolean isValid(String credential) {
        return this.credential.equals(Md5Util.genMd5(credential));
    }
}
