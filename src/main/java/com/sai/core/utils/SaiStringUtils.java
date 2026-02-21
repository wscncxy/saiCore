package com.sai.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * <b>字符串工具类</b>
 *
 */
@SuppressWarnings("all")
public class SaiStringUtils {

    private static Logger log = LoggerFactory.getLogger(SaiStringUtils.class);

    /**
     * 判断对象是否Empty(null或元素为0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isEmpty(Object pObj) {
        if (pObj == null)
            return true;
        if (pObj == "")
            return true;
        if (pObj instanceof java.lang.String) {
            if (((java.lang.String) pObj).length() == 0) {
                return true;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection) pObj).size() == 0) {
                return true;
            }
        } else if (pObj instanceof Map) {
            if (((Map) pObj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断对象是否为NotEmpty(!null或元素>0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isNotEmpty(Object pObj) {
        if (pObj == null)
            return false;
        if (pObj == "")
            return false;
        if (pObj instanceof java.lang.String) {
            if (((java.lang.String) pObj).length() == 0) {
                return false;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection) pObj).size() == 0) {
                return false;
            }
        } else if (pObj instanceof Map) {
            if (((Map) pObj).size() == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(String str, String prefix) {
        if (prefix != null) {
            return prefix.equals(str);
        }
        if (str != null) {
            return str.equals(prefix);
        }
        return true;
    }

    public static boolean equalsIgnoreCase(String str, String prefix) {
        if (prefix != null) {
            return prefix.equalsIgnoreCase(str);
        }
        if (str != null) {
            return str.equalsIgnoreCase(prefix);
        }
        return true;
    }

    public static boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }

    public static boolean isNotBlank(String str) {
        return StringUtils.isNotBlank(str);
    }

    public static boolean startsWith(String str, String prefix) {
        if (str == null || prefix == null) return false;
        return str.startsWith(prefix);
    }
}