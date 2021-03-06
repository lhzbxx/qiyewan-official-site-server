package com.qiyewan.core.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 区域
 */
@Entity
@Data
public class Region {
    @Id
    @GeneratedValue
    private Long id;
    // 省
    private String province;
    // 城市
    private String city;
    // 城市编号
    private String cityCode;
    // 区
    private String district;

    @Override
    public String toString() {
        return this.province + this.city + this.district;
    }
}
