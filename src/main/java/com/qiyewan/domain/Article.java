package com.qiyewan.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    // 文章摘要
    private String summary;

    // 主要内容
    private String content;

    public Article() {}

}
