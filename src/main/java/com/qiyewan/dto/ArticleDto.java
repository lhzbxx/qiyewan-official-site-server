package com.qiyewan.dto;

import com.qiyewan.domain.Article;
import lombok.Data;

import java.util.Date;

/**
 * Created by atom on 2016/11/7.
 */
public @Data class ArticleDto {
    private Long id;
    private String title;
    private String cover;
    private String summary;
    private String content;
    private String author;
    private String category;
    private Integer viewers;
    private Date createAt;
    private Date updateAt;
    private Article pre;
    private Article next;
}