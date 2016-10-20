package com.qiyewan.enums;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 订单状态
 */

public enum OrderState {

    // 未付款
    Unpaid,
    // 已付款 - 未评价
    Paid,
    // 已付款 - 完成评价
    Reviewed,
    // 超时
    Timeout

}
