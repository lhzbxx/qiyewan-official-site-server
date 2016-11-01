package com.qiyewan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 产品流程
 */

@Entity
@Data
public class ProductProcess {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    // 产品ID
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Product product;

    // 顺序
    private Integer sequence;

    // 内容
    private String content;

    public ProductProcess() {}

}
