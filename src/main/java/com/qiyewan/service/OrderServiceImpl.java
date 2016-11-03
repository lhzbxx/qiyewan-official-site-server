package com.qiyewan.service;

import com.qiyewan.domain.Order;
import com.qiyewan.enums.OrderState;
import com.qiyewan.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lhzbxx on 2016/10/28.
 *
 * 订单
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Page<Order> getOrdersByUser(Long userId, Pageable pageable) {
        return orderRepository.findByUserId(userId, pageable);
    }

    @Override
    public Page<Order> getOrdersByUserAndState(Long userId, OrderState orderState, Pageable pageable) {
        return orderRepository.findByUserIdAndOrderState(userId, orderState, pageable);
    }

    @Override
    public Order saveOrder(Long userId, Order order) {
        orderRepository.saveAndFlush(order);
        order.generateSerial();
        orderRepository.save(order);
        return order;
    }

    @Override
    public void saveOrders(List<Order> orderList) {
        orderRepository.save(orderList);
    }

}
