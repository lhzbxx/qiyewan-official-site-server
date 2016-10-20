package com.qiyewan.repository;

import com.qiyewan.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 产品
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}