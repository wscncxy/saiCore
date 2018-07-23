package com.sai.core.utils;

import org.apache.commons.lang3.StringUtils;

public class PasswordUtil {

    public static String encrypt(String pwd) {
        if (StringUtils.isBlank(pwd)) {
            return null;
        }
        return CryptoUtil.encrypt(pwd);
    }


    public static String decrypt(String loginToken) {
        if (StringUtils.isBlank(loginToken)) {
            return null;
        }
        return CryptoUtil.decrypt(loginToken);
    }
}
