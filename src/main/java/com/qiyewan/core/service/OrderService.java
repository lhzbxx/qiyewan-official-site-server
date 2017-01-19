package com.qiyewan.core.service;

import com.qiyewan.common.enums.OrderStage;
import com.qiyewan.core.domain.Order;
import com.qiyewan.core.other.payload.PaymentPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 订单
 */
public interface OrderService {
    Page<Order> getOrders(Pageable pageable);

    Page<Order> searchOrders(String query, Pageable pageable);

    Page<Order> getOrdersByUser(Long userId, Pageable pageable);

    Page<Order> getOrdersByUserAndState(Long userId, OrderStage orderStage, Pageable pageable);

    Order getOrderBySerialId(Long userId, String serialId);

    Order changePayment(Long userId, PaymentPayload paymentPayload);

    Order finishOrderBySerialId(String serialId);

    Order createAndSaveOrder(Long userId, Order order);

    Order saveOrder(Order order);

    void cancelOrder(Long userId, String serialId);

    void removeOrder(Long userId, String serialId);
}
