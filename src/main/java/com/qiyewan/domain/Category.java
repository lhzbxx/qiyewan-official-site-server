package com.qiyewan.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 文章-分类
 */

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    // 分类名称
    private String name;

    public Category() {}

}
