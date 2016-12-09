package com.qiyewan.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lhzbxx on 2016/11/2.
 *
 * 短信记录
 */
@Entity
@Data
public class Sms {
    @Id
    @GeneratedValue
    private Long id;
    private String phone;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createAt = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateAt = new Date();

    public Sms() {}

    public Sms(String phone, String content) {
        this.phone = phone;
        this.content = content;
    }
}
