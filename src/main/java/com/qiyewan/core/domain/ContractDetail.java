package com.qiyewan.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 合同详情
 */
@Entity
@Data
public class ContractDetail {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    private String contractServiceId;
    private String productServiceId;
    private String productServiceName;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updDate;

    public ContractDetail() {}
}
