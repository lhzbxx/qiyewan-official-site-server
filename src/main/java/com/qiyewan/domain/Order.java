package com.qiyewan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qiyewan.enums.OrderState;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by lhzbxx on 2016/10/18.
 *
 * 订单
 */

@Entity(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    // 用户ID
    private Long userId;

    // 区域编号
    private String regionCode;

    // 订单编号
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(unique = true)
    private String serialId;

    // 产品编号
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

    // 数量量词
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String unit;

    // 描述
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String summary;

    // 封面
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String cover;

    // 订单状态
    @JsonIgnore
    private OrderState orderState = OrderState.Unpaid;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateAt = new Date();

    public Order() {}

    public Order(Long userId, Cart cart) {
        this.userId = userId;
        this.regionCode = cart.getRegionCode();
        this.amount = cart.getAmount();
        Product product = cart.getProduct();
        this.name = product.getName();
        this.productSerialId = product.getSerialId();
        this.unitPrice = product.getUnitPrice();
        this.unit = product.getUnit();
        this.summary = product.getSummary();
        this.cover = product.getCover();
    }

    public void generateSerial() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        this.serialId = sdf.format(this.createAt)
                + (int) (Math.random() * 1000)
                + this.id
                + (int) (Math.random() * 1000);
    }

}
