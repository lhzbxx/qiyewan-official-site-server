package com.qiyewan.service;

import com.qiyewan.domain.Order;
import com.qiyewan.enums.OrderState;
import com.qiyewan.exceptions.IllegalActionException;
import com.qiyewan.exceptions.NotFoundException;
import com.qiyewan.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public Order getOrderBySerialId(Long userId, String serialId) {
        Order order = orderRepository.findBySerialId(serialId);
        checkOrder(userId, order);
        return order;
    }

    @Override
    public Order saveOrder(Long userId, Order order) {
        orderRepository.saveAndFlush(order);
        order.generateSerial();
        orderRepository.saveAndFlush(order);
        return order;
    }

    @Override
    public void saveOrders(List<Order> orderList) {
        orderRepository.save(orderList);
    }

    @Override
    public BigDecimal fee(BigDecimal totalFee, Order order) {
        if (order.getProductSerialId().substring(4).equals("XXXX")) {
            // TODO: 2016/11/3 特殊的计算方式！
            return BigDecimal.ONE;
        }
        return totalFee.add(order.getUnitPrice().multiply(BigDecimal.valueOf(order.getAmount())));
    }

    private void checkOrder(Long userId, Order order) {
        if (order == null)
            throw new NotFoundException("Error.Order.NOT_EXIST");
        if ( ! userId.equals(order.getUserId()))
            throw new IllegalActionException("Error.Order.NOT_YOUR_ORDER");
    }

}
