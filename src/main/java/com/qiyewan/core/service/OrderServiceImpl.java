package com.qiyewan.core.service;

import com.qiyewan.common.enums.OrderStage;
import com.qiyewan.common.exceptions.InvalidRequestException;
import com.qiyewan.common.exceptions.NotFoundException;
import com.qiyewan.common.utils.CrmUtil;
import com.qiyewan.common.utils.PayUtil;
import com.qiyewan.common.utils.SmsUtil;
import com.qiyewan.core.domain.*;
import com.qiyewan.core.other.payload.PaymentPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/28.
 *
 * 订单
 */

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> searchOrders(String query, Pageable pageable) {
        return orderRepository.findBySerialIdLike(query, pageable);
    }

    @Override
    public Page<Order> getOrdersByUser(Long userId, Pageable pageable) {
        return orderRepository.findByUserIdAndIsDeletedFalse(userId, pageable);
    }

    @Override
    public Page<Order> getOrdersByUserAndState(Long userId, OrderStage orderStage, Pageable pageable) {
        return orderRepository.findByUserIdAndIsDeletedFalseAndOrderStage(userId, orderStage, pageable);
    }

    @Override
    public Order getOrderBySerialId(Long userId, String serialId) {
        Order order = orderRepository.findBySerialId(serialId);
        checkOrder(userId, order);
        return order;
    }

    @Override
    public Order changePayment(Long userId, PaymentPayload paymentPayload) {
        Order order = orderRepository.findBySerialId(paymentPayload.getSerialId());
        checkOrder(userId, order);
        if (order.getPayment().equals(paymentPayload.getPayment())) {
            return order;
        }
        order.setPayment(paymentPayload.getPayment());
        try {
            order.setCharge(PayUtil.charge(order, paymentPayload.getOpenId()).toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidRequestException("请求支付失败。");
        }
        order.setUpdateAt(new Date());
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order finishOrderBySerialId(String serialId) {
        Order order = orderRepository.findBySerialId(serialId);
        if (order.getOrderStage().equals(OrderStage.UNPAID)) {
            order.setOrderStage(OrderStage.PAID);
            order.setUpdateAt(new Date());
            orderRepository.save(order);
            try {
                User user = userRepository.findOne(order.getUserId());
                if (user.getCustomerId() == null) {
                    user.generateCustomerId();
                    CrmUtil.createCustomer(user);
                }
                CrmUtil.createOrder(user.getCustomerId(), user.getPhone(), order);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String what = "";
            for (OrderDetail detail : order.getDetails()) {
                what += detail.getRegion() + ">>>" + detail.getName() + "|";
            }
            try {
                SmsUtil.send("17317131563", "用户" + userRepository.findOne(order.getUserId()).getPhone()
                        + "下了订单：" + serialId + what);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return order;
    }

    @Override
    public Order createAndSaveOrder(Long userId, Order order) {
        orderRepository.saveAndFlush(order);
        order.generateSerial();
        return order;
    }

    @Override
    public Order saveOrder(Order order) {
        orderRepository.save(order);
        return order;
    }

    @Override
    public void cancelOrder(Long userId, String serialId) {
        Order order = orderRepository.findBySerialId(serialId);
        checkOrder(userId, order);
        order.setOrderStage(OrderStage.CANCELED);
        orderRepository.save(order);
    }

    @Override
    public void removeOrder(Long userId, String serialId) {
        Order order = orderRepository.findBySerialId(serialId);
        checkOrder(userId, order);
        order.setIsDeleted(true);
        orderRepository.save(order);
    }

    private void checkOrder(Long userId, Order order) {
        if (order == null)
            throw new NotFoundException("该笔订单不存在。");
        if (!userId.equals(order.getUserId()))
            throw new InvalidRequestException("无法更改别人的订单。");
    }
}
