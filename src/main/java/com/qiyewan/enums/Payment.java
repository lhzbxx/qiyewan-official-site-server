package com.qiyewan.enums;

import com.qiyewan.exceptions.InvalidParamException;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 支付方式
 */
public enum Payment {
    // 支付宝
    AliPay,
    //微信支付
    WeChat_Mobile,
    //微信支付
    WeChat_PC;

    public Payment convert(String s) {
        try {
            return Payment.valueOf(s);
        } catch (Exception e) {
            throw new InvalidParamException("不支持的支付方式。");
        }
    }
}
