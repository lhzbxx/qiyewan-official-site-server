package com.qiyewan.core.service;

import com.qiyewan.core.domain.Company;
import com.qiyewan.core.domain.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lhzbxx on 2016/10/28.
 *
 * 公司/企业
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company show(Long userId) {
        return companyRepository.findByUserId(userId);
    }

    @Override
    public Company save(Long userId, Company company) {
        Company c = companyRepository.findByUserId(userId);
        c.reset(company);
        companyRepository.save(c);
        return c;
    }
}
