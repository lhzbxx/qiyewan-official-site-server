package com.qiyewan.core.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * Created by lhzbxx on 2016/11/7.
 *
 * Base64 -> Long
 */
public class Base64ToLongDeserializer extends JsonDeserializer<Long> {
    @Override
    public Long deserialize(JsonParser jp, DeserializationContext context)
            throws IOException {
        byte[] bt;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            bt = decoder.decodeBuffer(jp.getText());
            return Long.parseLong(new String(bt));
        } catch (Exception e){
            return null;
        }
    }
}
