package com.qiyewan.open.web;

import com.qiyewan.common.utils.IP2RegionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 公开的API。
 */

@RestController
public class PublicController {
    @Autowired
    private HttpServletRequest request;

    @CrossOrigin
    @GetMapping(value = "/locate.do")
    public String locate() {
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = request.getRemoteAddr();
        } else {
            ip = ip.split(",")[0];
        }
        return new IP2RegionUtil(ip).toRegionCode();
    }

    @CrossOrigin
    @GetMapping(value = "/")
    @ResponseBody
    public String check() {
        return "{\n" +
                "  \"status\": \"OK\",\n" +
                "  \"IP\": \"106.75.11.210\"\n" +
                "}";
    }
}
