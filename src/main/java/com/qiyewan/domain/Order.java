package com.qiyewan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qiyewan.enums.OrderState;
import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

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

    // 订单编号
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(unique = true, length = 30)
    private String serialId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderDetail> details;

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

    public Order(Long userId) {
        this.userId = userId;
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
