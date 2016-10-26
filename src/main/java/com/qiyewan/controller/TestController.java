package com.qiyewan.controller;

import com.qiyewan.utils.Ip2RegionUtil;
import com.qiyewan.utils.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 用户-身份认证
 */

@RestController
public class TestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        return "OK";
    }

    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    public String ip() {
        return new Ip2RegionUtil("59.78.46.141").toRegion();
    }

    @RequestMapping(value = "/sms", method = RequestMethod.GET)
    public String sms() {
        try {
            return SmsUtil.send("13651608916", "TEST");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "网络错误！";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String write() {
        stringRedisTemplate.opsForValue().set("key", "fuck you!");
        return "OK";
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read() {
        return stringRedisTemplate.opsForValue().get("key");
    }



}
