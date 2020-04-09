package com.sai.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * 单次线程流水号 reqNo,respNo工具
 */
public class ThreadLocalSessionStorage {
    /**
     * 单次线程流水号,后端自动生成
     */
    private static final ThreadLocal<String> THREAD_LOCAL_CONTEXT = new ThreadLocal<>();

    /**
     * 获取uuid线程号
     * 
     * @return
     */
    public static String getCurrentContext() {
        String context = THREAD_LOCAL_CONTEXT.get();
        if (StringUtils.isBlank(context)) {
            synchronized (THREAD_LOCAL_CONTEXT) {
                // double check
                context = THREAD_LOCAL_CONTEXT.get();
                if (StringUtils.isBlank(context)) {
                    context = initCurrentContext();
                }
            }
        }
        return context;

    }

    /**
     * 初始化uuid线程号
     *
     * @return
     */
    public static String initCurrentContext() {
        String context = UUID.randomUUID().toString();
        THREAD_LOCAL_CONTEXT.set(context);
        return context;
    }
}
