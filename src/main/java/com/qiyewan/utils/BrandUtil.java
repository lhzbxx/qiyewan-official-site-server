package com.qiyewan.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiyewan.dto.BrandDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by lhzbxx on 2016/11/25.
 *
 * 对接第三方服务。
 * 商标查询相关。
 */

public class BrandUtil {

    public static BrandDto fuzzyQuery(String keyword, int page) throws Exception {
        String url = "http://api.tmkoo.com/search.php";
        String apiKey = "4399320012393234";
        String apiPassword = "331nd3342d";
        url = url +
                "?keyword=" + URLEncoder.encode(keyword, "UTF-8") +
                "&apiKey=" + apiKey +
                "&apiPassword=" + apiPassword +
                "&pageSize=30" +
                "&pageNo=" + page;
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
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.toString(), BrandDto.class);
    }

    public static String accurateQuery(String regNo, String intCls) throws Exception {
        String url = "http://api.tmkoo.com/info.php";
        String apiKey = "4399320012393234";
        String apiPassword = "331nd3342d";
        url = url +
                "?regNo=" + regNo +
                "&intCls=" + intCls +
                "&apiKey=" + apiKey +
                "&apiPassword=" + apiPassword;
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
