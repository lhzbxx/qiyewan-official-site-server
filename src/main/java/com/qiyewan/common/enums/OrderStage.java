package com.qiyewan.common.enums;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 订单状态
 */
public enum OrderStage {
    // 未付款
    UNPAID,
    // 已付款（未评价）
    PAID,
    // 已付款（完成评价）
    REVIEWED,
    // 超时
    TIMEOUT,
    // 被取消
    CANCELED
}
