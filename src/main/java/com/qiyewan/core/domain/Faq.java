package com.qiyewan.core.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 常见问题
 */
@Entity
@Data
public class Faq {
    @Id
    @GeneratedValue
    private Long id;
    // 产品编号
    private String serialId;
    // 问题
    private String question;
    // 回答
    private String answer;
}
