package com.qiyewan.dto;

import com.qiyewan.domain.Product;
import lombok.Data;

/**
 * Created by lhzbxx on 2016/11/10.
 *
 * （导航栏）产品的简要信息
 */

@Data
public class Simple1ProductDto {

    private String name;

    private String serialId;

    private String classificationName;

    private String classificationCode;

    public Simple1ProductDto() {}

    public Simple1ProductDto(Product product) {
        this.name = product.getName();
        this.serialId = product.getSerialId();
        this.classificationCode = product.getClassificationCode();
        this.classificationName = product.getClassificationName();
    }

}
