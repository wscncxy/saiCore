package com.sai.core.utils;


public class RedisKey {
    private static final String SPLIT_STR = ":";
    private String progam;
    private String operation;
    private String sign;

    public static RedisKey create() {
        RedisKey redisKey = new RedisKey();
        return redisKey;
    }

    public RedisKey setProgam(String progam) {
        this.progam = progam;
        return this;
    }

    public RedisKey setOperation(String operation) {
        this.operation = operation;
        return this;
    }

    public RedisKey setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public String build() {
        return this.progam + SPLIT_STR + this.operation + SPLIT_STR + this.sign;
    }
}