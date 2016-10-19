package com.qiyewan.repository;

import com.qiyewan.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 公司/企业
 */

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
