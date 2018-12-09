package com.sai.core.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class JSONUtil {

    public static JSONObject toJSONObject(String str) throws JSONException{
        try {
            return JSONObject.parseObject(str);
        } catch (JSONException e) {
        }
        return null;
    }
}
