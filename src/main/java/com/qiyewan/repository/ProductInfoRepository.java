package com.qiyewan.repository;

import com.qiyewan.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 产品详情
 */

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {
}
