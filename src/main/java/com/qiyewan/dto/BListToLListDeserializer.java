package com.qiyewan.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhzbxx on 2016/11/7.
 *
 * List<Base64> -> List<Long>
 */
class BListToLListDeserializer extends JsonDeserializer<List<Long>> {

    @Override
    public List<Long> deserialize(JsonParser jp,
                                  DeserializationContext context)
            throws IOException {
        List<Long> list = new ArrayList<>();
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(jp);
            for (final JsonNode elem: jsonNode) {
                list.add(Long.parseLong(new String(decoder.decodeBuffer(elem.asText()))));
            }
            return list;
        } catch (Exception e){
            return null;
        }
    }

}
