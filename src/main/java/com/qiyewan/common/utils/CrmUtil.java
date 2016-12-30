package com.qiyewan.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiyewan.core.domain.Order;
import com.qiyewan.core.domain.User;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

/**
 * Created by lhzbxx on 2016/10/20.
 *
 * crm-joint
 */
public class CrmUtil {
    public static void createCustomer(User user) throws Exception {
        String url = "http://127.0.0.1:8082/customers";
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(new ObjectMapper().writeValueAsString(user));
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        HttpClients.createDefault().execute(httpPost);
    }

    public static void createOrder(String customerId, String mobile, Order order) throws Exception {
        String url = "http://127.0.0.1:8082/orders?customerId=" + customerId
                + "&mobile=" + mobile;
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(new ObjectMapper().writeValueAsString(order));
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        HttpClients.createDefault().execute(httpPost);
    }
}
