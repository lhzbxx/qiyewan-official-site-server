package com.qiyewan.repository;

import com.qiyewan.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 订单
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
