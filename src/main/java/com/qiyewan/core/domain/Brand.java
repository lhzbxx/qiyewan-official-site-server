package com.qiyewan.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qiyewan.core.dto.BrandDto;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lhzbxx on 2016/11/25.
 *
 * 商标查询-缓存。
 */
@Data
@Entity
public class Brand {
    @Id
    @GeneratedValue
    private Long id;
    // 关键词
    private String keyword;
    // 页数
    private Integer page = 1;
    // 所有数量
    private Integer allRecords;
    // 查询结果
    @Column(columnDefinition = "TEXT")
    private String results;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();
    // 查询次数
    private Integer count = 1;

    public Brand() {
    }

    public Brand(String keyword, int page, BrandDto brandDto) {
        this.keyword = keyword;
        this.page = page;
        this.allRecords = Integer.parseInt(brandDto.getAllRecords());
        this.results = brandDto.getResults();
    }
}
