package com.qiyewan.common.utils.Ip2Region;

import lombok.Data;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 新浪
 * IP->Region
 * 返回结果
 */

@Data
class IpLookupResult {
    private String country;
    private String province;
    private String city;
    private String district;
    private String desc;
    private String isp;
    private int ret;
    private int start;
    private int end;
    private String type;

    public IpLookupResult() {}

    @Override
    public String toString() {
        return this.country + " " + this.province + " " + this.city;
    }
}
