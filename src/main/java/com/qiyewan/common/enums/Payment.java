package com.qiyewan.common.enums;

import com.qiyewan.common.exceptions.InvalidParamException;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 支付方式
 */
public enum Payment {
    // 支付宝
    ALIPAY("alipay_pc_direct"),
    ALIPAY_WAP("alipay_wap"),
    //微信支付
    WXPAY("wx_pub_qr"),
    WXPAY_WAP("wx_pub"),
    UPACP("upacp"),
    UPACP_WAP("upacp_wap");
    private final String channel;

    Payment(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return this.channel;
    }

    public Payment convert(String s) {
        try {
            return Payment.valueOf(s);
        } catch (Exception e) {
            throw new InvalidParamException("不支持的支付方式。");
        }
    }
}
