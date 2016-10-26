package com.qiyewan.service;

import com.qiyewan.dto.AuthDto;
import com.qiyewan.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 消息队列-服务
 */

@Component
public class RabbitService {

    @RabbitListener(queues = "sms-queue")
    public void sendSMS(AuthDto authDto) {
        try {
            SmsUtil.send(authDto.getPhone(), "您的验证码是" + authDto.getCaptcha() + ", 15分钟内有效。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
