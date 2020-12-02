package com.sai.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sai.core.utils.es.SearchUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class JSONUtil {
    private static final Logger log = LoggerFactory.getLogger(SearchUtils.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static String toJSONString(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        }catch (Exception e){
            log.warn("JSONUtil toJSONString error",e);
        }
        return null;
    }

    public static <T> T strToObj(String jsonStr, Class<T> cla){
        try {
            return objectMapper.readValue(jsonStr, cla);
        }catch (Exception e){
            log.warn("JSONUtil toJSONString error",e);
        }
        return null;
    }

    public static Map<String, Object> getJSONMap(String jsonStr){
        try {
            return objectMapper.readValue(jsonStr, Map.class);
        }catch (Exception e){
            log.warn("JSONUtil toJSONString error",e);
        }
        return null;
    }

}
