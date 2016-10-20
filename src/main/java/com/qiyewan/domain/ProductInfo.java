package com.qiyewan.domain;

import com.qiyewan.enums.InfoType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 产品信息
 */

@Entity
@Data
public class ProductInfo {

    @Id
    @GeneratedValue
    private Long id;

    private Long productId;

    private InfoType infoType;

    private String url;

    private String title;

    private String content;

}
