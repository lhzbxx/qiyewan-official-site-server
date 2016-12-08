package com.qiyewan.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

/**
 * Created by lhzbxx on 2016/11/25.
 *
 * 对接第三方接口-商标查询。
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrandDto {
    private String allRecords;
    private String logId;
    private String msg;
    private String remainCount;
    @JsonRawValue
    private Object results;
    private Integer ret;

    public String getResults() {
        return this.results == null ? null : this.results.toString();
    }

    public void setResults(JsonNode node) {
        this.results = node;
    }
}
