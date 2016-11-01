package com.qiyewan.api;

import com.qiyewan.domain.Order;
import com.qiyewan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    private OrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public Page<Order> show(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Long userId = (Long) request.getAttribute("userId");
        return orderService.getOrdersByUser(userId, pageable);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public Order add() {
        Long userId = (Long) request.getAttribute("userId");
        Order order = new Order();
        return orderService.saveOrder(userId, order);
    }

}
