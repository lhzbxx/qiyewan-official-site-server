package com.qiyewan.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by lhzbxx on 2016/10/18.
 *
 * 订单-详情
 */
@Entity
@Data
public class OrderDetail {
    @Id
    @GeneratedValue
    private Long id;
    @JsonIgnore
    @ManyToOne
    private Order order;
    // 区域名称
    private String region;
    // 区域编号
    private String regionCode;
    // 产品编号
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String productSerialId;
    // 产品名称
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String name;
    // 数量
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer amount;
    // 单价
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal unitPrice;
    // 最低人数
    @ColumnDefault(value = "1")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer minMember = 1;
    // 每人价格
    @ColumnDefault(value = "0")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal perPrice = BigDecimal.ZERO;
    // 数量量词
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String unit;
    // 描述
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String summary;
    // 封面
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String cover;
    // 是否已评价
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isReviewed = false;
    // 参与人数
    private Integer member = 1;

    public OrderDetail() {}

    public OrderDetail(Cart cart) {
        this.region = cart.getRegion();
        this.regionCode = cart.getRegionCode();
        this.amount = cart.getAmount();
        this.member = cart.getMember();
        Product product = cart.getProduct();
        this.name = product.getName();
        this.productSerialId = product.getSerialId();
        this.unitPrice = product.getUnitPrice();
        this.minMember = product.getMinMember();
        this.perPrice = product.getPerPrice();
        this.unit = product.getUnit();
        this.summary = product.getSummary();
        this.cover = product.getCover();
    }
}
