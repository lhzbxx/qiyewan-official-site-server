package com.qiyewan.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    private Long areaId;

    // 订单编号
    private Long serialId;

}
