package com.qiyewan.core.web.controller;

import com.qiyewan.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
//
//    @CrossOrigin
//    @PostMapping("/orders/alipay/redirect")
//    public String alipay(@RequestParam Map<String, String> sParaTemp) {
//        if (AlipayNotify.verify(sParaTemp)) {
//            orderService.finishOrderBySerialId(sParaTemp.get("out_trade_no"));
//        }
//        return "success";
//    }
//
//    @CrossOrigin
//    @PostMapping("/orders/wechat/redirect")
//    public String wechat(@RequestBody String xml) {
//        try {
//            if (Signature.checkIsSignValidFromResponseString(xml)) {
//                orderService.finishOrderBySerialId((String) XMLParser.getMapFromXML(xml).get("out_trade_no"));
//            }
//        } catch (ParserConfigurationException | IOException | SAXException e) {
//            e.printStackTrace();
//        }
//        return "<xml>\n" +
//                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
//                "  <return_msg><![CDATA[OK]]></return_msg>\n" +
//                "</xml>";
//    }
}
