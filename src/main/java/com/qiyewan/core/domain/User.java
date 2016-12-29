package com.qiyewan.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qiyewan.core.other.payload.UserInfoPayload;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
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
    // 是否绑定微信
    @ColumnDefault(value = "false")
    private Boolean isWxBound = false;
    // 客户（主）编号
    private String customerId;
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

    public void generateCustomerId() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        this.customerId = "W"
                + sdf.format(this.createAt).substring(2, 5)
                + this.id;
    }

    public void reset(UserInfoPayload userInfoPayload) {
        this.avatar = userInfoPayload.getAvatar();
        this.nickname = userInfoPayload.getNickname();
    }
}
