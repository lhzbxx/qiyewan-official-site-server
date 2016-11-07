package com.qiyewan.service;

import com.qiyewan.domain.Order;
import com.qiyewan.domain.OrderDetail;
import com.qiyewan.enums.OrderState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 订单
 */

public interface OrderService {

    Page<Order> getOrdersByUser(Long userId, Pageable pageable);

    Page<Order> getOrdersByUserAndState(Long userId, OrderState orderState, Pageable pageable);

    Order getOrderBySerialId(Long userId, String serialId);

    Order finishOrderBySerialId(String serialId);

    Order createAndSaveOrder(Long userId, Order order);

    Order saveOrder(Order order);

    void deleteOrder(Long userId, String serialId);

    BigDecimal fee(BigDecimal totalFee, OrderDetail orderDetail);

}
