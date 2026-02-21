package com.sai.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sai.core.utils.es.v8.SearchUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
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

    public static <T> T parseObject(String jsonStr, Class<T> cla) {
        try {
            return objectMapper.readValue(jsonStr, cla);
        } catch (Exception e) {
            log.warn("JSONUtil parseObject error", e);
        }
        return null;
    }

    public static <T> List<T> parseArray(String jsonStr, Class<T> cla) {
        try {
            TypeReference<List<T>> typeReference = new TypeReference<>() {
            };
            return objectMapper.readValue(jsonStr, typeReference);
        } catch (Exception e) {
            log.warn("JSONUtil toJSONString error", e);
        }
        return null;
    }

    public static Map<String, Object> getJSONMap(String jsonStr) {
        try {
            return objectMapper.readValue(jsonStr, Map.class);
        } catch (Exception e) {
            log.warn("JSONUtil toJSONString error", e);
        }
        return null;
    }

}
