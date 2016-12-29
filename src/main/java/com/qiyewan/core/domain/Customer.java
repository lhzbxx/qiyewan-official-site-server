package com.qiyewan.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 客户（个人/公司）信息
 */
@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    // 客户编号
    private String customerId;
    // 客户名称
    private String name;
    // 客户类型
    private String type;
    // 客户地址
    private String address;
    // 公司名称
    private String companyName;
    // 法人代表
    private String legalRepresentative;
    // 公司地址
    private String companyAddress;
    // 员工人数
    private String employeeAmount;
    // 注册资本
    private String registeredCapital;
    // 营业执照
    private String businessLicense;
    // 税务登记号
    private String taxRegistration;
    // 企业电话
    private String telephone;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt = new Date();

    public Customer() {}
}
