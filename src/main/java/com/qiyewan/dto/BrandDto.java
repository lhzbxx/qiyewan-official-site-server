package com.qiyewan.dto;

import lombok.Data;

/**
 * Created by lhzbxx on 2016/11/25.
 *
 * 对接第三方接口-商标查询。
 */

@Data
public class BrandDto {

    private Integer allRecords;

    private String msg;

    private Integer remainCount;

    private String results;

    private Integer ret;

}
