package com.qiyewan.domain;

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

    // 区域
    private String district;

    public String address() {
        return this.province + this.city + this.district;
    }

}
