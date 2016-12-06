package com.qiyewan.repository;

import com.qiyewan.domain.Order;
import com.qiyewan.enums.OrderStage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 订单
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByUserId(Long userId, Pageable pageable);

    Page<Order> findByUserIdAndOrderStage(Long userId, OrderStage orderStage, Pageable pageable);

    Order findBySerialId(String serialId);
}
