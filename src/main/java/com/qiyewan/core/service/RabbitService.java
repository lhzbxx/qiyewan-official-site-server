package com.qiyewan.core.service;

import com.qiyewan.core.domain.LoginHistory;
import com.qiyewan.core.domain.Order;
import com.qiyewan.core.domain.Sms;
import com.qiyewan.core.other.payload.PhonePayload;
import com.qiyewan.common.enums.OrderStage;
import com.qiyewan.core.domain.LoginHistoryRepository;
import com.qiyewan.core.domain.OrderRepository;
import com.qiyewan.core.domain.SmsRepository;
import com.qiyewan.core.domain.UserRepository;
import com.qiyewan.common.utils.Ip2RegionUtil;
import com.qiyewan.common.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 消息队列-服务
 */
@Component
public class RabbitService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private LoginHistoryRepository loginHistoryRepository;
    @Autowired
    private SmsRepository smsRepository;
    @Value("${com.qiyewan.env}")
    private String env;

    // 注意！
    // 生产环境使用打印验证码的形式方便测试。
    // 正式环境中要解除这部分注释。
    @RabbitListener(queues = "sms-queue")
    public void sendCaptcha(PhonePayload phonePayload) {
        if (env.equals("dev")) {
            System.out.println(phonePayload.getCaptcha());
        } else {
            try {
                String content = "您的验证码是" + phonePayload.getCaptcha() + ", 15分钟内有效。";
                SmsUtil.send(phonePayload.getPhone(), content);
                smsRepository.save(new Sms(phonePayload.getPhone(), content));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RabbitListener(queues = "order-notify-queue")
    public void sendNotification(String serialId) {
        if (env.equals("dev")) return;
        Order order = orderRepository.findBySerialId(serialId);
        if (order == null) return;
        switch (order.getOrderStage()) {
            case UNPAID:
                try {
                    String phone = userRepository.findOne(order.getUserId()).getPhone();
                    String content = "您已下了订单号为" + order.getSerialId() + "，请及时支付。";
                    SmsUtil.send(phone, content);
                    smsRepository.save(new Sms(phone, content));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case PAID:
                try {
                    String phone = userRepository.findOne(order.getUserId()).getPhone();
                    String content = "您已支付成功，订单号为" + order.getSerialId() + "。满意请给好评，感谢您的使用！";
                    SmsUtil.send(phone, content);
                    smsRepository.save(new Sms(phone, content));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    @RabbitListener(queues = "order-timeout-queue")
    public void setTimeout(String serialId) {
        Order order = orderRepository.findBySerialId(serialId);
        if (order == null) return;
        if (order.getOrderStage() == OrderStage.UNPAID) {
            order.setOrderStage(OrderStage.TIMEOUT);
            order.setUpdateAt(new Date());
            orderRepository.save(order);
        }
    }

    @RabbitListener(queues = "login-history-record-queue")
    public void recordLogin(Long recordId) {
        if (env.equals("dev")) return;
        LoginHistory loginHistory = loginHistoryRepository.findOne(recordId);
        if (loginHistory == null) return;
        loginHistory.setAddress(new Ip2RegionUtil(loginHistory.getIp()).toRegion());
        loginHistoryRepository.save(loginHistory);
    }
}
