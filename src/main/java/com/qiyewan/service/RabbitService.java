package com.qiyewan.service;

import com.qiyewan.domain.Order;
import com.qiyewan.dto.AuthDto;
import com.qiyewan.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 消息队列-服务
 */

@Component
public class RabbitService {

    @Autowired
    private UserService userService;

    // 注意！
    // 生产环境使用打印验证码的形式方便测试。
    // 正式环境中要解除这部分注释。
    @RabbitListener(queues = "sms-queue")
    public void sendCaptcha(AuthDto authDto) {
        System.out.println(authDto.getCaptcha());
//        try {
//            SmsUtil.send(authDto.getPhone(), "您的验证码是" + authDto.getCaptcha() + ", 15分钟内有效。");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @RabbitListener(queues = "order-queue")
    public void sendNotification(Order order) {
        switch (order.getOrderState()) {
            case Unpaid:
                try {
                    SmsUtil.send(userService.getUserById(order.getUserId()).getPhone(),
                            "您已下了订单号为" + order.getSerialId().toString() + "，请及时支付。");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Paid:
                try {
                    SmsUtil.send(userService.getUserById(order.getUserId()).getPhone(),
                            "您已支付成功，订单号为" + order.getSerialId().toString() + "。满意请给好评，感谢您的使用！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

}
