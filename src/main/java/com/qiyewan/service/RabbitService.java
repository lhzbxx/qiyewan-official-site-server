package com.qiyewan.service;

import com.qiyewan.dto.AuthDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 消息队列-服务
 */

@Component
public class RabbitService {

    // 注意！
    // 生产环境使用打印验证码的形式方便测试。
    // 正式环境中要解除这部分注释。
    @RabbitListener(queues = "sms-queue")
    public void sendSMS(AuthDto authDto) {
        System.out.println(authDto.getCaptcha());
//        try {
//            SmsUtil.send(authDto.getPhone(), "您的验证码是" + authDto.getCaptcha() + ", 15分钟内有效。");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
