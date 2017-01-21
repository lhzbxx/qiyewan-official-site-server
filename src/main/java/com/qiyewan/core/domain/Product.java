package com.qiyewan.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qiyewan.common.enums.CityCode;
import com.qiyewan.common.enums.ProductState;
import com.qiyewan.core.other.payload.ProductPayload;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

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
    @Column(unique = true, length = 10)
    // 产品编号
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
    private ProductState productState = ProductState.ON_OFFER;
    // 单价
    private BigDecimal unitPrice;
    // 最低人数（以上采用 perPrice 计算）
    @ColumnDefault(value = "1")
    private Integer minMember;
    // 每人价格
    // 最终价格：(unitPrice + (member - minMember) * perPrice) * amount
    @ColumnDefault(value = "0")
    private BigDecimal perPrice;
    // 数量量词
    private String unit;
    // 是否热门
    private Boolean isHot = false;
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
    // 您需要提供...（文字版）
    private String tWhatNeed;
    // 您将得到...（文字版）
    private String tWhatObtain;
    // 流程...
    private String process;
    // 评分
    private Double rate = 5.0;
    // 购买人次
    private Integer purchaseNumber = 0;
    // 备注
    private String comment;
    // 版本
    @ColumnDefault(value = "2")
    @Column(columnDefinition = "TINYINT")
    private Integer version;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateAt = new Date();

    public Product() {}

    public Product(ProductPayload payload, CityCode cityCode) {
        this.serialId = payload.getSerialId();
        this.name = payload.getName();
        this.classificationCode = payload.getClassificationCode();
        this.classificationName = payload.getClassificationName();
        this.regionCode = cityCode.getCode();
        this.unitPrice = payload.getUnitPrice();
        this.minMember = payload.getMinMember();
        this.perPrice = payload.getPerPrice();
        this.unit = payload.getUnit();
        this.isInstant = payload.getIsInstant();
        this.summary = payload.getSummary();
        this.cover = "product-" + payload.getSerialId() + "-cover.jpg";
        this.whatNeed = "product-" + payload.getSerialId() + "-what-need.png";
        this.whatObtain = "product-" + payload.getSerialId() + "-what-obtain.png";
        this.tWhatNeed = payload.getWhatNeed();
        this.tWhatObtain = payload.getWhatObtain();
        this.process = payload.getProcess();
    }
}
