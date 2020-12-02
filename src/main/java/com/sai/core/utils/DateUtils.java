package com.sai.core.utils;

import java.util.concurrent.TimeUnit;

public class DateUtils {

    public static Long toMILLISECONDS(Long expireTime, TimeUnit timeUnit){
        switch (timeUnit){
            case DAYS -> expireTime = expireTime * 24 * 60 * 60 * 1000;
            case HOURS -> expireTime = expireTime * 60 * 60 * 1000;
            case MINUTES -> expireTime = expireTime * 60 * 1000;
            case SECONDS -> expireTime = expireTime * 1000;
        }
        return expireTime;
    }
}
