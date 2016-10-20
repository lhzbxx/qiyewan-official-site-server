package com.qiyewan.repository;

import com.qiyewan.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 购物车
 */

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}