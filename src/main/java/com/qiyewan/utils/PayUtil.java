package com.qiyewan.utils;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.Charge;
import com.qiyewan.domain.Order;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lhzbxx on 2016/12/8.
 *
 * 支付相关
 */
public class PayUtil {
    public static Charge charge(Order order) throws RateLimitException, APIException, ChannelException,
            InvalidRequestException, APIConnectionException, AuthenticationException {
        Pingpp.apiKey = "sk_test_iDKGyHKaP4CSDSab18LK8WfL";
//        Pingpp.privateKeyPath = "";
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("order_no", order.getSerialId());
        chargeParams.put("amount", 100);
        Map<String, String> app = new HashMap<>();
        app.put("id", "app_9Seb90CS0SW1D80K");
        chargeParams.put("app", app);
        chargeParams.put("channel", "alipay");
        chargeParams.put("currency", "cny");
        chargeParams.put("client_ip", "127.0.0.1");
        chargeParams.put("subject", "subject");
        chargeParams.put("body", "body");
        return Charge.create(chargeParams);
    }
}
