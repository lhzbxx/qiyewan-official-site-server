package com.qiyewan.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

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

    // 产品编号
    @NotNull
    private String productSerialId;

    // 订单编号
    @NotNull
    private String serialId;

    // 评论内容
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String content;

    // 评分
    @NotNull
    @Size(min = 1, max = 5)
    private Integer star = 5;

    @OneToMany
    private List<ReviewTag> tags;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();

}
