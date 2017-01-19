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
//        Pingpp.apiKey = "sk_test_iDKGyHKaP4CSDSab18LK8WfL";
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
        Map<String, Object> extra = new HashMap<>();
        switch (order.getPayment()) {
            case ALIPAY:
                extra.put("success_url", "http://www.qiyewan.com/success");
                break;
            case ALIPAY_WAP:
                extra.put("success_url", "http://www.qiyewan.com/#/success");
                extra.put("app_pay", true);
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
        if (subject.length() > 32) {
            subject = subject.substring(0, 32);
        }
        chargeParams.put("subject", subject);
        if (body.length() > 128) {
            body = body.substring(0, 128);
        }
        chargeParams.put("body", body);
        return Charge.create(chargeParams);
    }

    private static BigDecimal fee(BigDecimal totalFee, OrderDetail orderDetail) {
        // 最终价格：(unitPrice + (member - minMember) * perPrice) * amount
        Integer member = orderDetail.getMember() - orderDetail.getMinMember();
        if (member > 0) {
            return totalFee.add(
                    new BigDecimal(orderDetail.getAmount()).multiply(
                            orderDetail.getUnitPrice().add(
                                    orderDetail.getPerPrice().multiply(
                                            new BigDecimal(member)
                                    )
                            )
                    )
            ).add(orderDetail.getPremium()).setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            return totalFee.add(
                    new BigDecimal(orderDetail.getAmount()).multiply(orderDetail.getUnitPrice())
            ).add(orderDetail.getPremium()).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }
}
