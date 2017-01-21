package com.qiyewan.core.web;

import com.qiyewan.core.domain.Order;
import com.qiyewan.core.domain.OrderDetail;
import com.qiyewan.core.other.payload.PayPayload;
import com.qiyewan.core.other.dto.ResultDto;
import com.qiyewan.common.enums.OrderStage;
import com.qiyewan.common.exceptions.InvalidRequestException;
import com.qiyewan.core.other.payload.PaymentPayload;
import com.qiyewan.core.service.CartService;
import com.qiyewan.core.service.OrderService;
import com.qiyewan.common.utils.PayUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 订单
 */
@RestController
public class OrderController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @CrossOrigin
    @GetMapping(value = "/orders")
    public Page<Order> showList(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                @RequestParam(required = false) OrderStage state) {
        Long userId = (Long) request.getAttribute("userId");
        if (state == null)
            return orderService.getOrdersByUser(userId, pageable);
        return orderService.getOrdersByUserAndState(userId, state, pageable);
    }

    /**
     * 创建订单
     * @param payPayload
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/orders")
    @Transactional
    public Order add(@RequestBody PayPayload payPayload) {
        Long userId = (Long) request.getAttribute("userId");
        List<Long> carts = payPayload.getCarts();
        if (carts.isEmpty())
            throw new InvalidRequestException("订单内容为空。");
        Order order = new Order(userId);
        order.setComment(payPayload.getComment());
        order.setPayment(payPayload.getPayment());
        List<OrderDetail> details = new ArrayList<>();
        for (Long cartId : carts) {
            OrderDetail detail = cartService.convertToOrderDetail(userId, cartId);
            details.add(detail);
        }
        order.setDetails(details);
        cartService.deleteCarts(userId, carts);
        orderService.createAndSaveOrder(userId, order);
        try {
            order.setCharge(PayUtil.charge(order, payPayload.getOpenId()).toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidRequestException("请求支付失败。");
        }
        orderService.saveOrder(order);
        String out_trade_no = order.getSerialId();
        rabbitTemplate.convertAndSend("order-notify-queue", out_trade_no);
        rabbitTemplate.convertAndSend("order-timeout-exchange", "order-timeout-queue", out_trade_no, message -> {
            message.getMessageProperties().setDelay(3600000);
            return message;
        });
        return order;
    }

    @CrossOrigin
    @GetMapping("/orders/{serialId}")
    public Order show(@PathVariable String serialId) {
        Long userId = (Long) request.getAttribute("userId");
        return orderService.getOrderBySerialId(userId, serialId);
    }

    /**
     * 修改支付方式
     * @param paymentPayload
     * @return
     */
    @CrossOrigin
    @PatchMapping("/orders")
    public Order change(@RequestBody PaymentPayload paymentPayload) {
        Long userId = (Long) request.getAttribute("userId");
        return orderService.changePayment(userId, paymentPayload);
    }

    @CrossOrigin
    @DeleteMapping("/orders/{serialId}")
    public ResultDto cancel(@PathVariable String serialId) {
        Long userId = (Long) request.getAttribute("userId");
        orderService.cancelOrder(userId, serialId);
        return new ResultDto();
    }

    @CrossOrigin
    @DeleteMapping("/orders")
    public ResultDto remove(@RequestParam String serialId) {
        Long userId = (Long) request.getAttribute("userId");
        orderService.removeOrder(userId, serialId);
        return new ResultDto();
    }
}
