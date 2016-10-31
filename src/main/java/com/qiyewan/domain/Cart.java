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

    // 产品ID
    @NotNull(message = "Error.Param.NO_PRODUCT_ID")
    private Long productId;

    // 区域ID
    private Long regionId;

    // 数量
    private Integer amount;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();

    public Cart() {}

}
