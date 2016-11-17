package com.qiyewan.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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

    @ManyToOne
    private User user;

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
    @Min(1)
    @Max(5)
    private Integer star = 5;

    // 购买数量
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer amount = 1;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id")
    private List<ReviewTag> tags = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();

    public Review() {}

}
