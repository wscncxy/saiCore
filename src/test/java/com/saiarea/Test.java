package com.saiarea;

import com.alibaba.fastjson.JSONObject;
import com.sai.core.utils.ValidateUtil;

import java.math.BigDecimal;

/**
 * Created by ZhouXiang on 2018/3/1 0001 11:32.
 */
public class Test {
    public static void main(String[] arg){
        TestData testData= new TestData();
        testData.setId(new BigDecimal(1));
        testData.setName("1234567800");
        testData.setDesc("233");
        testData.setAge(9);
        testData.setAccountCount(10);
        System.out.println(JSONObject.toJSONString(ValidateUtil.valid(testData)));
    }
}
