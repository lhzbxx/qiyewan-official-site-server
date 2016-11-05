package com.qiyewan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 购物车
 */

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    // 用户ID
    @JsonIgnore
    private Long userId;

    // 产品编号
    @NotNull
    private String serialId;

    @ManyToOne
    private Product product;

    // 区域编号
    @NotNull
    private String regionCode;

    // 区域名称
    @NotNull
    private String region;

    // 数量
    @NotNull
    private Integer amount = 1;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();

    public Cart() {}

}
