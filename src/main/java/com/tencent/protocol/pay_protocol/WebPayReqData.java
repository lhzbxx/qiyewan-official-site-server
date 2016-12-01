package com.tencent.protocol.pay_protocol;

import com.tencent.common.Configure;
import com.tencent.common.RandomStringGenerator;
import com.tencent.common.Signature;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一下单-请求参数
 */
@Data
public class WebPayReqData {

    private String appid = "";
    private String mch_id = "";
    private String device_info = "WEB";
    private String nonce_str = "";
    private String sign = "";
    private String body = "";
    private String out_trade_no = "";
    private int total_fee = 0;
    private String spbill_create_ip = "106.75.11.210";

    /**
     * @param body       要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
     * @param outTradeNo 商户系统内部的订单号，32个字符内可包含字母, 确保在商户系统唯一
     * @param totalFee   订单总金额，单位为“分”
     */
    public WebPayReqData(String body, String outTradeNo, int totalFee) {
        //微信分配的公众号ID（开通公众号之后可以获取到）
        setAppid(Configure.getAppid());

        //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
        setMch_id(Configure.getMchid());

        //要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
        setBody(body);

        //商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
        setOut_trade_no(outTradeNo);

        //订单总金额，单位为“分”，只能整数
        setTotal_fee(totalFee);

        //随机字符串，不长于32 位
        setNonce_str(RandomStringGenerator.getRandomStringByLength(32));

        //根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap());

        //把签名数据设置到Sign这个属性中
        setSign(sign);
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if (obj != null) {
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
