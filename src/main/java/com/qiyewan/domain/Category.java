package com.qiyewan.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 文章-分类
 */

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    // 分类名称
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();

    public Category() {}

}
