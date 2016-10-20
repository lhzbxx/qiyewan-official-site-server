package com.qiyewan.controller;

import com.qiyewan.utils.Ip2RegionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 用户-身份认证
 */

@RestController
public class AuthController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        return "OK";
    }

    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    public String ip() {
        return new Ip2RegionUtil("59.78.46.141").toRegion();
    }

}
