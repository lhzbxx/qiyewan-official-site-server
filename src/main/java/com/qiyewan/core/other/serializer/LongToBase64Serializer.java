package com.qiyewan.core.other.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by lhzbxx on 2016/11/7.
 *
 * Long -> Base64
 */
public class LongToBase64Serializer extends JsonSerializer<Long> {
    @Override
    public void serialize(Long s,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException {
        if (s == null)
            return;
        BASE64Encoder encoder = new BASE64Encoder();
        jsonGenerator.writeString(encoder.encode(s.toString().getBytes()));
    }
}
