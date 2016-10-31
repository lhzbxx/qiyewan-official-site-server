package com.qiyewan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 产品信息
 */

@Entity
@Data
public class ProductInfo {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    // 产品ID
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Product product;

    // 标题
    private String title;

    // 主要内容
    private String content;

    public ProductInfo() {}

}
