package com.qiyewan.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 文章
 */

@Entity
@Data
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    // 文章标题
    private String title;

    // 封面（图片）
    private String cover;

    // 文章摘要
    private String summary;

    // 内容
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    // 作者
    private String author;

    // 分类
    private String category;

    // 浏览量
    private Integer viewers = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateAt = new Date();

    public Article() {}

}
