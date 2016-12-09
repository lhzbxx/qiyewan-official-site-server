package com.qiyewan.core.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 产品
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByRegionCode(String regionCode);

    List<Product> findAllByClassificationNameAndRegionCode(String classificationName,
                                                           String regionCode);

    List<Product> findAllByClassificationCodeAndRegionCode(String classificationCode,
                                                           String regionCode);

    @Query("SELECT DISTINCT classificationName FROM Product")
    List<String> findDistinctClassificationName();

    Product findBySerialId(String serialId);
}
