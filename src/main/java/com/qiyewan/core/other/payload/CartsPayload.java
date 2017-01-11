package com.qiyewan.core.other.payload;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.qiyewan.core.other.serializer.BListToLListDeserializer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 批量的购物车
 */
@Data
public class CartsPayload {
    @NotNull(message = "购物车内容不能为空。")
    @Size(min = 1)
    @JsonDeserialize(using = BListToLListDeserializer.class)
    private List<Long> carts;

    public CartsPayload() {}
}
