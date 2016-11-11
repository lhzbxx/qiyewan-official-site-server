package com.qiyewan.enums;

/**
 * Created by lhzbxx on 2016/11/10.
 *
 * 大分类
 */

public enum BigClassification {

    IC("工商服务"),

    FC("财税服务"),

    LD("法律服务"),

    HR("人事服务"),

    IT("IT&设计服务"),

    PS("套餐服务");

    private final String name;

    BigClassification(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
