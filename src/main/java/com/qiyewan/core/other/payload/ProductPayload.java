package com.qiyewan.core.other.payload;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 产品
 */
@Data
public class ProductPayload {
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
    // 是否立即交付
    private Boolean isInstant;
    // 描述
    private String summary;
    // 您需要提供...（文字版）
    private String whatNeed;
    // 您将得到...（文字版）
    private String whatObtain;
    // 流程...
    private String process;

    public ProductPayload() {}
}
