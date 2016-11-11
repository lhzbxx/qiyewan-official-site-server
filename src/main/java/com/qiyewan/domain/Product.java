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
    @Column(unique = true, length = 10)
    private String serialId;

    // 产品名称
    private String name;

    // （大）分类编号
    private String classificationCode;

    // 分类名称
    private String classificationName;

    // 区域编号
    private String regionCode;

    // 热度（排序）
    private Integer heat = 0;

    // 状态
    private ProductState productState = ProductState.PutAway;

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

    // 您需要提供...
    private String whatNeed;

    // 您将得到...
    private String whatObtain;

    // 流程...
    private String process;

    // 详细信息（Q&A形式）
    private String info;

    // 评分
    private Double rate = 0.0;

    // 购买人次
    private Integer purchaseNumber = 0;

    // 备注
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateAt = new Date();

    public Product() {}

}
