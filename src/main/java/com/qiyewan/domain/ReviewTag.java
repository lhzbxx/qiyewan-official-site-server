package com.qiyewan.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 评论-标签
 */

@Entity
@Data
public class ReviewTag {

    @Id
    @GeneratedValue
    private Long id;

    // 评论ID
    private Long reviewId;

    // 标签内容
    private String content;

}
