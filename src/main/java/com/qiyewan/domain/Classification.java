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

    // 分类名称
    private String name;

    // 分类代号
    private String code;

    // 父分类
    private Long parentId;

    // 分类编号
    // 3占位-NodeId-遍历
    private Long serialId;

}
