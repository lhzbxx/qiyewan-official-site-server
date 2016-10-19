package com.qiyewan.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 产品
 */

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Long classificationId;

    private String classificationName;

    // 单价
    private BigDecimal unitPrice;

    // 数量量词
    private String unit;

    // 描述
    private String summary;

    private String cover;

}
