package com.qiyewan.enums;

/**
 * Created by lhzbxx on 2016/11/2.
 *
 * （硬编码）地区代号
 */

public enum RegionCode {

    上海("SHSH"),
    北京("BJBJ"),
    深圳("GDSZ"),
    南京("JSNJ"),
    苏州("JSSZ"),
    成都("SCCD"),
    镇江("JSZJ");

    private final String code;

    RegionCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
