package com.sai.core.utils.cache;

public abstract class CacheUtil {

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     * @param expireTime 过期时间，单位秒
     * @return
     */
    public abstract boolean setKV(String key, Object value, Integer expireTime);

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setKV(String key, Object value) {
        return setKV(key, value, 10);
    }

    /**
     * 设置缓存
     *
     * @param key
     * @return
     */
    public boolean setKV(String key) {
        return setKV(key, null);
    }

    /**
     * 设置缓存
     *
     * @param key
     * @return
     */
    public boolean setKV(String key, Integer expireTime) {
        return setKV(key, null, expireTime);
    }

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setStr(String key, String value) {
        return setKV(key, value, 10);
    }


    public abstract Object getKV(String key);

    public Object getKVStr(String key) {
        Object value = getKV(key);
        if (value != null) {
            return value.toString();
        }
        return null;
    }

}
