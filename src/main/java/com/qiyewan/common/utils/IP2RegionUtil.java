package com.qiyewan.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiyewan.common.entities.IPLookupResult;
import com.qiyewan.common.enums.CityCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * IP->Region
 * 转换服务
 */
public class IP2RegionUtil {
    private String ip;

    public IP2RegionUtil(String ip) {
        this.ip = ip;
    }

    public String toRegion() {
        String response;
        IPLookupResult result;
        try {
            response = sendPost();
            result = parseResponse(response);
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String toRegionCode() {
        String response;
        IPLookupResult result;
        try {
            response = sendPost();
            result = parseResponse(response);
            return CityCode.convert(result.getCity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getUrl() {
        return "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=" + this.ip;
    }

    private String sendPost() throws IOException {
        URL obj = new URL(getUrl());
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    private IPLookupResult parseResponse(String response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response, IPLookupResult.class);
    }
}
