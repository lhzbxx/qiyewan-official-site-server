package com.qiyewan.core.other.payload;

import com.qiyewan.common.enums.Payment;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 支付方式
 */
@Data
public class PaymentPayload {
    @NotNull(message = "订单编号不能为空。")
    private String serialId;

    @NotNull(message = "支付方式不能为空。")
    @ConvertGroup(from = String.class, to = Payment.class)
    private Payment payment;

    private String openId;

    public PaymentPayload() {}
}
