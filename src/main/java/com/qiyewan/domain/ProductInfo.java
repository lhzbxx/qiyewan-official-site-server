package com.qiyewan.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 产品-详情
 */

@Entity
@Data
public class ProductInfo {

    @Id
    @GeneratedValue
    private Long id;

    public ProductInfo() {}

}
