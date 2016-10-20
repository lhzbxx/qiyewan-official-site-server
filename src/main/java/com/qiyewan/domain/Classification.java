package com.qiyewan.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 产品分类
 */

@Entity
@Data
public class Classification {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}