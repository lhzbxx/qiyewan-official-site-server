package com.qiyewan.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qiyewan.enums.ProductState;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    // 分类名称
    private String classificationCode;

    // 区域ID
    private String regionCode;

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
    private Boolean isInstant = false;

    // 描述
    private String summary;

    // 封面
    private String cover;

    // 您需要提供...
    private String whatNeed;

    // 您将得到...
    private String whatObtain;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    @PrimaryKeyJoinColumn
    private List<ProductProcess> process;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    @PrimaryKeyJoinColumn
    private List<ProductInfo> infoList;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateAt = new Date();

    public Product() {}

}
