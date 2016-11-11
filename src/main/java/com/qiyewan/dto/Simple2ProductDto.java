package com.qiyewan.dto;

import com.qiyewan.domain.Product;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by lhzbxx on 2016/11/10.
 *
 * （产品列表）产品的简要信息
 */

@Data
public class Simple2ProductDto {

    private String name;

    private String serialId;

    private String cover;

    private String summary;

    private BigDecimal price;

    public Simple2ProductDto() {}

    public Simple2ProductDto(Product product) {
        this.name = product.getName();
        this.serialId = product.getSerialId();
        this.cover = product.getCover();
        this.summary = product.getSummary();
        this.price = product.getUnitPrice();
    }

}
