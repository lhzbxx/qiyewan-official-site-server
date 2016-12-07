package com.qiyewan.domain;

import com.qiyewan.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lhzbxx on 2016/11/25.
 *
 * 商标查询-缓存。
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findFirstByKeywordAndPage(String keyword, int page);
}
