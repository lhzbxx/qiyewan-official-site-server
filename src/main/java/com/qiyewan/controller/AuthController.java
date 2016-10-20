package com.qiyewan.controller;

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

}
