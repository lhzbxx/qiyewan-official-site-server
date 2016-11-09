package com.qiyewan.api;

import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;
import com.qiyewan.domain.Order;
import com.qiyewan.domain.OrderDetail;
import com.qiyewan.dto.ErrorDto;
import com.qiyewan.dto.PayDto;
import com.qiyewan.enums.OrderState;
import com.qiyewan.exceptions.IllegalActionException;
import com.qiyewan.service.CartService;
import com.qiyewan.service.OrderService;
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
import java.util.Map;

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

    @CrossOrigin
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public Page<Order> showList(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                @RequestParam(required = false) OrderState state) {
        Long userId = (Long) request.getAttribute("userId");
        if (state == null)
            return orderService.getOrdersByUser(userId, pageable);
        return orderService.getOrdersByUserAndState(userId, state, pageable);
    }

    @CrossOrigin
    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public Order add(@RequestBody PayDto payDto) {
        Long userId = (Long) request.getAttribute("userId");
        List<Long> carts = payDto.getCarts();
        if (carts == null || carts.isEmpty())
            throw new IllegalActionException("Error.Cart.EMPTY_CARTS");
        Order order = new Order(userId);
        List<OrderDetail> details = new ArrayList<>();
        for (Long cartId: carts) {
            OrderDetail detail = cartService.convertToOrderDetail(userId, cartId);
            details.add(detail);
        }
        order.setDetails(details);
        cartService.deleteCarts(userId, carts);
        orderService.createAndSaveOrder(userId, order);
        String out_trade_no = order.getSerialId();
        OrderDetail detail = details.get(0);
        String subject = detail.getName();
        String body = detail.getProductSerialId() + "*" + detail.getAmount();
        BigDecimal total_fee = BigDecimal.ZERO;
        total_fee = orderService.fee(total_fee, detail);
        if (details.size() > 1) {
            for (OrderDetail o: details.subList(1, details.size() - 1)) {
                subject += " + " + o.getName();
                body += " + " + o.getProductSerialId() + "*" + o.getAmount();
                total_fee = orderService.fee(total_fee, o);
            }
        }
        rabbitTemplate.convertAndSend("order-notify-queue", out_trade_no);
        rabbitTemplate.convertAndSend("order-timeout-exchange", "order-timeout-queue", out_trade_no, message -> {
            message.getMessageProperties().setDelay(3600000);
            return message;
        });
        order.setPayUrl(AlipaySubmit.buildLink(out_trade_no, subject, body, total_fee.toString()));
        order.setTotalPrice(total_fee);
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
    public ErrorDto remove(@PathVariable String serialId) {
        Long userId = (Long) request.getAttribute("userId");
        orderService.deleteOrder(userId, serialId);
        return new ErrorDto();
    }

}
