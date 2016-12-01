package com.tencent.service;

import com.tencent.protocol.pay_protocol.WebPayReqData;

public class WebPayService extends BaseService {
    public WebPayService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super("https://api.mch.weixin.qq.com/pay/unifiedorder");
    }

    /**
     * 请求支付服务
     *
     * @param webPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String request(WebPayReqData webPayReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(webPayReqData);

        return responseString;
    }
}
