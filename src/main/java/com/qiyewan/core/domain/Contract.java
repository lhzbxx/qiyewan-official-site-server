package com.qiyewan.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 合同（服务单）
 */
@Entity
@Data
public class Contract {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    private String sno;
    private String area;
    private String contractSno;
    private String boPid;
    private String productSeries;
    private String currentProductServiceNode;
    private String status;
    private String customerId;
    private String servicePerson;
    private String startMonth;
    private String endMonth;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updDate;

    public Contract() {}
}
