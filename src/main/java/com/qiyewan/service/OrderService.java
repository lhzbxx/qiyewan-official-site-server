package com.qiyewan.service;

import com.qiyewan.domain.Order;
import com.qiyewan.enums.OrderState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 订单
 */

public interface OrderService {

    Page<Order> getOrdersByUser(Long userId, Pageable pageable);

    Page<Order> getOrdersByUserAndState(Long userId, OrderState orderState, Pageable pageable);

    Order saveOrder(Long userId, Order order);

    void saveOrders(List<Order> orderList);

}
