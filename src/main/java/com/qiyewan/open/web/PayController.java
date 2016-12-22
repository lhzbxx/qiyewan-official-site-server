package com.qiyewan.open.web;

import com.pingplusplus.model.Event;
import com.pingplusplus.model.Webhooks;
import com.qiyewan.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/pay-redirect.do")
    public String alipay(@RequestBody String params) {
        Event event = Webhooks.eventParse(params);
        if (event.getType().equals("charge.succeeded")) {
            System.out.println(event.getData().getObject().toString());
        }
        return "success";
    }
}
