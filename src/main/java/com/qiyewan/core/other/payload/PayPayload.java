package com.qiyewan.core.other.payload;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.qiyewan.common.enums.Payment;
import com.qiyewan.core.other.serializer.BListToLListDeserializer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import java.util.List;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 生成订单的格式
 */
@Data
public class PayPayload {
    @NotNull(message = "订单内容不能为空。")
    @Size(min = 1)
    @JsonDeserialize(using = BListToLListDeserializer.class)
    private List<Long> carts;

    @NotNull(message = "支付方式不能为空。")
    @ConvertGroup(from = String.class, to = Payment.class)
    private Payment payment;

    @Size(max = 255, message = "备注的内容过长！")
    private String comment;

    private String openId;

    public PayPayload() {}
}
