package com.qiyewan.dto;

import com.qiyewan.domain.Product;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by lhzbxx on 2016/11/10.
 *
 * 产品的简要信息
 */

@Data
public class SimpleProductDto {

    private String name;

    private String serialId;

    private String cover;

    private String summary;

    private BigDecimal price;

    public SimpleProductDto() {}

    public SimpleProductDto(Product product) {
        this.name = product.getName();
        this.serialId = product.getSerialId();
        this.cover = product.getSerialId();
        this.summary = product.getSummary();
        this.price = product.getUnitPrice();
    }

}
