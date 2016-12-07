package com.qiyewan.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.qiyewan.enums.Payment;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    private Payment payment;

    public PayPayload() {}
}
