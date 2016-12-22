package com.qiyewan.open.web;

import com.pingplusplus.util.WxpubOAuth;
import com.qiyewan.common.utils.WxUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 中间人代理
 */
@RestController
public class ProxyController {
    @CrossOrigin
    @GetMapping(value = "/proxy/wx/access-token")
    public String getWxAccessToken(@RequestParam String code) throws Exception {
        return WxUtil.getAccessToken(code);
    }

    @CrossOrigin
    @GetMapping(value = "/proxy/wx/user-info")
    public String getWxUserInfo(@RequestParam String accessToken,
                                @RequestParam String openId) throws Exception {
        return WxUtil.getUserInfo(accessToken, openId);
    }

    @CrossOrigin
    @GetMapping(value = "/wx/oauth")
    @Deprecated
    public ModelAndView wxOAuth() throws Exception {
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:" +
                WxpubOAuth.createOauthUrlForCode("wx6d3c480373a5418f", "http://www.qiyewan.com:8081/wx/open-id", true));
        return view;
    }

    @CrossOrigin
    @GetMapping(value = "/wx/open-id")
    @Deprecated
    public String wxOpenId(@RequestParam String code) throws UnsupportedEncodingException {
        return WxpubOAuth.getOpenId("wx6d3c480373a5418f", "18621c68bcaaebbda2292cdb1595cc56", code);
    }
}
