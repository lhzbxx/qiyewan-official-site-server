package com.qiyewan.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.qiyewan.enums.Payment;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 生成订单的格式
 */

@Data
public class PayDto {

    @NotNull
    @JsonDeserialize(using = BListToLListDeserializer.class)
    private List<Long> carts;

    private Payment payment;

    public PayDto() {}

}
