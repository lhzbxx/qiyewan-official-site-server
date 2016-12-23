package com.qiyewan.core.service;

import com.qiyewan.core.domain.*;
import com.qiyewan.core.other.payload.PhonePayload;
import com.qiyewan.common.enums.OrderStage;
import com.qiyewan.common.utils.IP2RegionUtil;
import com.qiyewan.common.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
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
        String what = "";
        for (OrderDetail detail: order.getDetails()) {
            what += detail.getName() + "，";
        }
        switch (order.getOrderStage()) {
            case UNPAID:
                try {
                    String phone = userRepository.findOne(order.getUserId()).getPhone();
                    String content = "您已下单购买产品：" + what + "请及时支付。";
                    SmsUtil.send(phone, content);
                    smsRepository.save(new Sms(phone, content));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case PAID:
                try {
                    String phone = userRepository.findOne(order.getUserId()).getPhone();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
                    String content = "您于" + dateFormat.format(order.getUpdateAt()) +
                            "成功购买产品：" + what +
                            "服务人员将于6小时内联系您（工作日），请您保持手机畅通！谢谢！";
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
        loginHistory.setAddress(new IP2RegionUtil(loginHistory.getIp()).toRegion());
        loginHistoryRepository.save(loginHistory);
    }
}
