package com.qiyewan.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qiyewan.common.enums.OrderStage;
import com.qiyewan.common.enums.Payment;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    // 订单编号
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(unique = true, length = 30)
    private String serialId;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<OrderDetail> details;
    // 支付方式
    // （默认为支付宝）
    private Payment payment = Payment.ALIPAY;
    // 订单状态
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OrderStage orderStage = OrderStage.UNPAID;
    // 总价
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal totalPrice = BigDecimal.ZERO;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateAt = new Date();
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(columnDefinition = "TEXT")
    private String charge;
    // 备注
    private String comment;
    // 软删除
    @ColumnDefault(value = "false")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isDeleted = false;

    public Order() {}

    public Order(Long userId) {
        this.userId = userId;
    }

    public void generateSerial() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        String no = "";
        int tmp = (int) (Math.log10(this.id) + 1);
        for (int i = 0; i < (7 - tmp); i++) {
            no += "0";
        }
        this.serialId = "S"
                + sdf.format(this.createAt).substring(2, 6)
                + no + this.id;
    }
}
