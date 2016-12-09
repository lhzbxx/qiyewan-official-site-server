package com.qiyewan.core.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 顾客声音
 */

@Entity
@Data
public class CustomerVoice {
    @Id
    @GeneratedValue
    private Long id;
    // 头像
    private String avatar;
    // 顾客（公司）名称
    private String name;
    // 合作业务
    private String business;
    // 内容
    private String content;
    // 背景
    private String background;
    // 备注
    private String comment;
}
