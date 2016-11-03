package com.qiyewan.api;

import com.alipay.util.AlipaySubmit;
import com.qiyewan.domain.Order;
import com.qiyewan.dto.ErrorDto;
import com.qiyewan.enums.OrderState;
import com.qiyewan.exceptions.IllegalActionException;
import com.qiyewan.service.CartService;
import com.qiyewan.service.OrderService;
import com.sun.istack.internal.Nullable;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 购物车
 */

@RestController
public class OrderApi {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public Page<Order> showList(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                @RequestParam @Nullable OrderState state) {
        Long userId = (Long) request.getAttribute("userId");
        if (state == null)
            return orderService.getOrdersByUser(userId, pageable);
        return orderService.getOrdersByUserAndState(userId, state, pageable);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ErrorDto<String> add(@RequestBody List<Long> carts) {
        Long userId = (Long) request.getAttribute("userId");
        if (carts.isEmpty())
            throw new IllegalActionException("Error.Cart.EMPTY_CARTS");
        List<Order> orders = new ArrayList<>();
        for (Long cartId: carts) {
            Order order = cartService.convertToOrder(userId, cartId);
            orders.add(order);
        }
        cartService.deleteCarts(userId, carts);
        Order order = orders.remove(0);
        orderService.saveOrder(userId, order);
        String out_trade_no = order.getSerialId();
        String subject = order.getName();
        String body = order.getProductSerialId() + "*" + order.getAmount();
        BigDecimal total_fee = BigDecimal.ZERO;
        total_fee = orderService.fee(total_fee, order);
        for (Order o: orders) {
            subject += " + " + o.getName();
            body += " + " + o.getProductSerialId() + "*" + order.getAmount();
            o.setSerialId(order.getSerialId());
            total_fee = orderService.fee(total_fee, o);
        }
        orderService.saveOrders(orders);
        rabbitTemplate.convertAndSend("order-queue", out_trade_no);
        return new ErrorDto<>(AlipaySubmit.buildLink(out_trade_no, subject, body, total_fee.toString()));
    }

    @GetMapping("/orders/{serialId")
    public Order show(@PathVariable String serialId) {
        Long userId = (Long) request.getAttribute("userId");
        return orderService.getOrderBySerialId(userId, serialId);
    }

    @PostMapping("/orders/{serialId}")
    public void pay(@PathVariable String serialId) {
    }

}
