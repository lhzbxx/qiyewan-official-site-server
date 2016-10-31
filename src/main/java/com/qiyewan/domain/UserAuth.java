package com.qiyewan.domain;

import com.qiyewan.utils.Md5Util;
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

    private String identifier;

    private String credential;

    private String salt;

    public UserAuth() {}

    public UserAuth(Long userId, String identifier, String credential) {
        this.salt = UUID.randomUUID().toString();
        this.userId = userId;
        this.identifier = identifier;
        this.credential = Md5Util.genMd5(credential + this.salt);
    }

    public boolean isValid(String credential) {
        return this.credential.equals(Md5Util.genMd5(credential + this.salt));
    }

}
