package com.qiyewan.domain;

import com.qiyewan.enums.OrderState;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

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

    // 区域ID
    private Long regionId;

    // 订单编号
    private Long serialId;

    // 产品ID
    private Long productId;

    // 数量
    private Integer amount;

    // 单价
    private BigDecimal unitPrice;

    // 数量量词
    private String unit;

    // 描述
    private String summary;

    // 封面
    private String cover;

    // 订单状态
    private OrderState orderState = OrderState.Unpaid;

}
