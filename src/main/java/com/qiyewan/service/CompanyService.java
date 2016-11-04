package com.qiyewan.service;

import com.qiyewan.domain.Company;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 公司/企业
 */

public interface CompanyService {

    Company show(Long userId);

    Company save(Long userId, Company company);

}
