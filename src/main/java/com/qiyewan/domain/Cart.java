package com.qiyewan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qiyewan.dto.Base64ToLongDeserializer;
import com.qiyewan.dto.LongToBase64Serializer;
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
    @JsonSerialize(using = LongToBase64Serializer.class)
    @JsonDeserialize(using = Base64ToLongDeserializer.class)
    private Long id;
    // 用户ID
    @JsonIgnore
    private Long userId;
    // 产品编号
    @NotNull(message = "产品编号不能为空。")
    private String serialId;
    @ManyToOne
    private Product product;
    // 区域编号
    @NotNull(message = "区域编号不能为空。")
    private String regionCode;
    // 区域名称
    @NotNull(message = "区域名称不能为空。")
    private String region;
    // 数量
    @NotNull(message = "数量不能为空。")
    private Integer amount = 1;
    // 参与人数
    @NotNull(message = "人数不能为空。")
    private Integer member = 1;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateAt = new Date();

    public Cart() {}

    public Cart copy(Cart cart) {
        this.updateAt = new Date();
        this.amount = cart.amount;
        return this;
    }
}
