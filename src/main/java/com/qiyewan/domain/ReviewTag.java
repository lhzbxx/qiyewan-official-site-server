package com.qiyewan.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
    // 产品编号
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String productSerialId;
    // 评论ID
    @ManyToOne
    private Review review;
    // 标签内容
    private String content;

    public ReviewTag() {}
}
