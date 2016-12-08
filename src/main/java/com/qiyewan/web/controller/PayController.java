package com.qiyewan.web.controller;

import com.alipay.util.AlipayNotify;
import com.qiyewan.service.OrderService;
import com.tencent.common.Signature;
import com.tencent.common.XMLParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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
        if (AlipayNotify.verify(sParaTemp)) {
            orderService.finishOrderBySerialId(sParaTemp.get("out_trade_no"));
        }
        return "success";
    }

    @CrossOrigin
    @PostMapping("/orders/wechat/redirect")
    public String wechat(@RequestBody String xml) {
        try {
            if (Signature.checkIsSignValidFromResponseString(xml)) {
                orderService.finishOrderBySerialId((String) XMLParser.getMapFromXML(xml).get("out_trade_no"));
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return "<xml>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                "</xml>";
    }
}
