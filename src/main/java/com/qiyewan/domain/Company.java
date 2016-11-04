package com.qiyewan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 公司/企业信息
 */

@Entity
@Data
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    // 用户ID
    @JsonIgnore
    @Column(unique = true)
    private Long userId;

    // 公司名称
    private String name;

    // 法人代表
    private String legalRepresentative;

    // 公司地址
    private String address;

    // 注册资本
    private String registeredCapital;

    // 营业执照
    private String businessLicense;

    // 税务登记号
    private String taxRegistration;

    // 企业电话
    private String telephone;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateAt = new Date();

    public Company() {}

    public Company(Long userId) {
        this.userId = userId;
    }

}
