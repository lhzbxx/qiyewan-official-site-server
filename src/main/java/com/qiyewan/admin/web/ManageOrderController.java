package com.qiyewan.admin.web;

import com.qiyewan.core.domain.Order;
import com.qiyewan.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 订单管理
 */
@RestController
@RequestMapping("/admin")
public class ManageOrderController {
    @Autowired
    private OrderService orderService;

    @CrossOrigin
    @GetMapping("/orders")
    public Page<Order> getOrders(@RequestParam(required = false) String query,
                                 Pageable pageable) {
        if (query == null) {
            return orderService.getOrders(pageable);
        }
        return orderService.searchOrders(query, pageable);
    }
}
