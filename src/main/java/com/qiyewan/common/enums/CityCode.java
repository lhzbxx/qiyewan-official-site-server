package com.qiyewan.common.enums;

/**
 * Created by lhzbxx on 2016/11/2.
 *
 * （硬编码）城市编号
 */
public enum CityCode {
    北京("BJBJ"),
    上海("SHSH"),
    南京("JSNJ"),
    苏州("JSSZ"),
    镇江("JSZJ"),
    深圳("GDSZ"),
    成都("SCCD"),
    合肥("AHHF");
    private final String code;

    CityCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static String convert(String s) {
        try {
            return CityCode.valueOf(s).code;
        } catch (Exception e) {
            return CityCode.上海.code;
        }
    }
}
