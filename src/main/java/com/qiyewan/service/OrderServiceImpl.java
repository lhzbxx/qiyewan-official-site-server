package com.qiyewan.service;

import com.qiyewan.domain.Order;
import com.qiyewan.domain.OrderDetail;
import com.qiyewan.enums.OrderState;
import com.qiyewan.exceptions.IllegalActionException;
import com.qiyewan.exceptions.NotFoundException;
import com.qiyewan.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/28.
 * <p>
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
    public Order finishOrderBySerialId(String serialId) {
        Order order = orderRepository.findBySerialId(serialId);
        if (order.getOrderState().equals(OrderState.Unpaid)) {
            order.setOrderState(OrderState.Paid);
            order.setUpdateAt(new Date());
            orderRepository.save(order);
        }
        return order;
    }

    @Override
    public Order createAndSaveOrder(Long userId, Order order) {
        orderRepository.saveAndFlush(order);
        order.generateSerial();
        orderRepository.saveAndFlush(order);
        return order;
    }

    @Override
    public Order saveOrder(Order order) {
        orderRepository.save(order);
        return order;
    }

    @Override
    public void deleteOrder(Long userId, String serialId) {
        Order order = orderRepository.findBySerialId(serialId);
        checkOrder(userId, order);
        order.setOrderState(OrderState.Canceled);
        orderRepository.save(order);
    }

    @Override
    public BigDecimal fee(BigDecimal totalFee, OrderDetail orderDetail) {
        BigDecimal amount = new BigDecimal(orderDetail.getAmount());
        if (orderDetail.getProductSerialId().substring(4).equals("HR0003")) {
            if (orderDetail.getMember() > 3) {
                return totalFee.add((new BigDecimal(98.8)
                        .add(new BigDecimal(18.8)
                                .multiply(new BigDecimal(orderDetail.getMember() - 3))))
                        .multiply(amount));
            } else {
                return totalFee.add(new BigDecimal(98.8).multiply(amount));
            }
        }
        return totalFee.add(orderDetail.getUnitPrice().multiply(amount));
    }

    private void checkOrder(Long userId, Order order) {
        if (order == null)
            throw new NotFoundException("Error.Order.NOT_EXIST");
        if (!userId.equals(order.getUserId()))
            throw new IllegalActionException("Error.Order.NOT_YOUR_ORDER");
    }

}
