package com.qiyewan.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * 短信服务
 */

public class SmsUtil {

    public static String send(String mobile, String msg) throws Exception {
        String url = "http://222.73.117.156:80/msg/HttpBatchSendSM";
        String account = "vip-zjxx";
        String password = "Tch574923";
        url = url +
                "?account=" + account +
                "&pswd=" + password +
                "&mobile=" + mobile +
                "&msg=" + msg +
                "&needstatus=true" +
                "&product=&extno=";
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

}