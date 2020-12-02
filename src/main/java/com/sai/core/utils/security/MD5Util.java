package com.sai.demo.util.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

public class MD5Util {

    private static final Logger log = LoggerFactory.getLogger(MD5Util.class);

    public MD5Util() {
    }

    public static String getMD5(String str) {
        if (str == null) {
            return null;
        } else {
            try {
                StringBuilder md5StrBuff = new StringBuilder("");
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.reset();
                md.update(str.getBytes("UTF-8"));
                byte[] byteArray = md.digest();

                for(int i = 0; i < byteArray.length; ++i) {
                    if (Integer.toHexString(255 & byteArray[i]).length() == 1) {
                        md5StrBuff.append("0").append(Integer.toHexString(255 & byteArray[i]));
                    } else {
                        md5StrBuff.append(Integer.toHexString(255 & byteArray[i]));
                    }
                }

                return md5StrBuff.toString();
            } catch (Exception var5) {
                log.warn("getMD5 error:{}", var5);
                return null;
            }
        }
    }

    public static String MD5(String s) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for(int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return (new String(str)).toLowerCase();
        } catch (Exception var10) {
            log.warn("MD5 error:{}", var10);
            return null;
        }
    }

    public static String getSignature(Map params, String appId) {
        Object[] keySet = params.keySet().toArray();
        Arrays.sort(keySet);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < keySet.length; ++i) {
            if (!"sign".equals(keySet[i])) {
                sb.append(keySet[i]).append("=");
                sb.append(params.get(keySet[i])).append("&");
            }
        }

        return getMD5(sb.deleteCharAt(sb.lastIndexOf("&")).toString() + appId);
    }
}
