package com.qiyewan.service;

import com.qiyewan.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 订单
 */

public interface OrderService {

    Page<Order> getOrdersByUser(Long userId, Pageable pageable);

}
