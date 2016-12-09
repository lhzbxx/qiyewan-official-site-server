package com.qiyewan.admin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qiyewan.common.enums.AdminRole;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 管理员
 */
@Entity
@Data
public class Admin {
    // 管理员ID
    @Id
    @GeneratedValue
    private Long id;
    // 管理员昵称
    @NotNull(message = "昵称不能为空。")
    private String nickname;
    // 账号
    @NotNull(message = "账号不能为空。")
    private String account;
    // 密码
    private String password;
    // 权限
    @NotNull(message = "权限不能为空。")
    private AdminRole role;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateAt = new Date();

    public Admin() {}
}
