package com.qiyewan.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qiyewan.enums.ProductState;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

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

    // 产品编号
    private String serialId;

    // 产品名称
    private String name;

    // 分类ID
    private Long classificationId;

    // 分类名称
    private String classificationName;

    // 区域ID
    private Long regionId;

    // 热度（排序）
    private Integer heat;

    // 状态
    private ProductState productState;

    // 单价
    private BigDecimal unitPrice;

    // 数量量词
    private String unit;

    // 是否热门
    private Boolean isHot;

    // 是否立即交付
    private Boolean isInstant;

    // 描述
    private String summary;

    // 封面
    private String cover;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateAt = new Date();

}
