package com.qiyewan.core.web;

import com.qiyewan.core.domain.Order;
import com.qiyewan.core.domain.OrderDetail;
import com.qiyewan.core.other.payload.PayPayload;
import com.qiyewan.core.other.dto.ResultDto;
import com.qiyewan.common.enums.OrderStage;
import com.qiyewan.common.exceptions.InvalidRequestException;
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
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public Page<Order> showList(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                @RequestParam(required = false) OrderStage state) {
        Long userId = (Long) request.getAttribute("userId");
        if (state == null)
            return orderService.getOrdersByUser(userId, pageable);
        return orderService.getOrdersByUserAndState(userId, state, pageable);
    }

    @CrossOrigin
    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    @Transactional
    public Order add(@RequestBody PayPayload payPayload) {
        Long userId = (Long) request.getAttribute("userId");
        List<Long> carts = payPayload.getCarts();
        if (carts.isEmpty())
            throw new InvalidRequestException("Error.Cart.EMPTY_CARTS");
        Order order = new Order(userId);
        order.setPayment(payPayload.getPayment());
        List<OrderDetail> details = new ArrayList<>();
        for (Long cartId : carts) {
            OrderDetail detail = cartService.convertToOrderDetail(userId, cartId);
            details.add(detail);
        }
        String out_trade_no = order.getSerialId();
        order.setDetails(details);
        cartService.deleteCarts(userId, carts);
        orderService.createAndSaveOrder(userId, order);
        rabbitTemplate.convertAndSend("order-notify-queue", out_trade_no);
        rabbitTemplate.convertAndSend("order-timeout-exchange", "order-timeout-queue", out_trade_no, message -> {
            message.getMessageProperties().setDelay(3600000);
            return message;
        });
        try {
            order.setCharge(PayUtil.charge(order).toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidRequestException("请求支付失败。");
        }
        orderService.saveOrder(order);
        return order;
    }

    @CrossOrigin
    @GetMapping("/orders/{serialId}")
    public Order show(@PathVariable String serialId) {
        Long userId = (Long) request.getAttribute("userId");
        return orderService.getOrderBySerialId(userId, serialId);
    }

    @CrossOrigin
    @DeleteMapping("/orders/{serialId}")
    public ResultDto remove(@PathVariable String serialId) {
        Long userId = (Long) request.getAttribute("userId");
        orderService.deleteOrder(userId, serialId);
        return new ResultDto();
    }
}
