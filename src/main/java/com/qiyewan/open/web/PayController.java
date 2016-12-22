package com.qiyewan.open.web;

import com.qiyewan.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by lhzbxx on 2016/11/9.
 *
 * 第三方支付
 */
@RestController
public class PayController {
    @Autowired
    private OrderService orderService;
    @CrossOrigin
    @PostMapping("/orders/alipay/redirect")
    public String alipay(@RequestParam Map<String, String> sParaTemp) {
        return "success";
    }
}
