package com.qiyewan.common.utils;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.Charge;
import com.qiyewan.core.domain.Order;
import com.qiyewan.core.domain.OrderDetail;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lhzbxx on 2016/12/8.
 *
 * 支付相关
 */
public class PayUtil {
    public static Charge charge(Order order, String openId) throws RateLimitException, APIException, ChannelException,
            InvalidRequestException, APIConnectionException, AuthenticationException {
        Pingpp.apiKey = "sk_live_aLSWz5iPmLiTj108GGrj1S0G";
//        Pingpp.privateKeyPath = "";

        List<OrderDetail> details = order.getDetails();
        OrderDetail detail = details.get(0);
        String subject = detail.getName();
        String body = detail.getProductSerialId() + "*" + detail.getAmount();
        BigDecimal total_fee = BigDecimal.ZERO;
        total_fee = PayUtil.fee(total_fee, detail);
        if (details.size() > 1) {
            for (OrderDetail o : details.subList(1, details.size())) {
                subject += " + " + o.getName();
                body += " + " + o.getProductSerialId() + "*" + o.getAmount();
                total_fee = PayUtil.fee(total_fee, o);
            }
        }
        order.setTotalPrice(total_fee);

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("order_no", order.getSerialId());
        chargeParams.put("amount", total_fee.multiply(BigDecimal.valueOf(100).setScale(0, RoundingMode.HALF_UP)));
        Map<String, String> app = new HashMap<>();
        Map<String, String> extra = new HashMap<>();
        switch (order.getPayment()) {
            case ALIPAY:
                extra.put("success_url", "http://www.qiyewan.com/success");
                break;
            case ALIPAY_WAP:
                extra.put("success_url", "http://www.qiyewan.com/#/success");
                break;
            case WXPAY:
                extra.put("product_id", order.getSerialId());
                break;
            case WXPAY_WAP:
                extra.put("open_id", openId);
                break;
        }
        app.put("id", "app_9Seb90CS0SW1D80K");
        chargeParams.put("app", app);
        chargeParams.put("extra", extra);
        chargeParams.put("channel", order.getPayment().getChannel());
        chargeParams.put("currency", "cny");
        chargeParams.put("client_ip", "106.75.11.210");
        chargeParams.put("subject", subject);
        chargeParams.put("body", body);
        return Charge.create(chargeParams);
    }

    private static BigDecimal fee(BigDecimal totalFee, OrderDetail orderDetail) {
        BigDecimal amount = new BigDecimal(orderDetail.getAmount());
        if (orderDetail.getProductSerialId().substring(4).equals("HR0003")) {
            if (orderDetail.getMember() > 3) {
                return totalFee.add((new BigDecimal(98.8)
                        .add(new BigDecimal(18.8)
                                .multiply(new BigDecimal(orderDetail.getMember() - 3))))
                        .multiply(amount))
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
            } else {
                return totalFee.add(new BigDecimal(98.8).multiply(amount))
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
            }
        }
        return totalFee.add(orderDetail.getUnitPrice().multiply(amount))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
