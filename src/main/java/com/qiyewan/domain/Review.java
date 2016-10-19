package com.qiyewan.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 评论
 */

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long productId;

    // 评论内容
    private String content;

    // 评分
    private Integer star = 5;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();

}
