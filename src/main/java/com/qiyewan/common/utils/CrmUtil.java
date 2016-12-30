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
        String url = "http://127.0.0.1:8002/customers";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(new ObjectMapper().writeValueAsString(user)));
        HttpClients.createDefault().execute(httpPost);
    }

    public static void createOrder(String customerId, Order order) throws Exception {
        String url = "http://127.0.0.1:8002/orders?customerId=" + customerId;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(new ObjectMapper().writeValueAsString(order)));
        HttpClients.createDefault().execute(httpPost);
    }
}
