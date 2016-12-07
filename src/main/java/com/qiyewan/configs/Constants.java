package com.qiyewan.configs;

/**
 * Created by atom on 2016/11/7.
 *
 * 常量
 */

public class Constants {
    public static final String REDIS_PREFIX_PRODUCTS = "products:";

    public static String REDIS_PRODUCTS_CITY(String regionCode) {
        return "products:" + regionCode;
    }

    public static String REDIS_PRODUCTS_CITY_CLASSIFICATION(String regionCode, String classificationName) {
        return "products:" + regionCode + ":" + classificationName;
    }
}
