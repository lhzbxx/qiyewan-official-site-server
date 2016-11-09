package com.qiyewan.controller;

import com.alipay.util.AlipayNotify;
import com.qiyewan.service.OrderService;
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
    public String pay(@RequestParam Map<String, String> sParaTemp) {
        System.out.println(sParaTemp);
        if (AlipayNotify.verify(sParaTemp)) {
            orderService.finishOrderBySerialId(sParaTemp.get("out_trade_no"));
        }
        return "success";
    }

}
