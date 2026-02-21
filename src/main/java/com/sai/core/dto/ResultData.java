package com.sai.core.dto;

import com.sai.core.constants.StatusConstant;

import java.beans.Transient;

public class ResultData<T> {

    /**
     * Created by ZhouXiang on 2017/8/15 0015 13:38.
     */

    private String code;
    private String msg;
    private T data;

    public ResultData() {
    }

    public ResultData(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Transient
    public boolean isSuccess() {
        return org.apache.commons.lang3.StringUtils.equals(StatusConstant.RESULT_SUCCESS_CODE, code);
    }

    public static <T> ResultData<T> fail(String msg) {
        return new ResultData<T>(StatusConstant.RESULT_FAIL_CODE, msg, null);
    }

    public static <T> ResultData<T> fail(String code, String msg) {
        return new ResultData<T>(code, msg, null);
    }

    public static <T> ResultData<T> success(String msg, T data) {
        return new ResultData<T>(StatusConstant.RESULT_SUCCESS_CODE, msg, data);
    }

    public static <T> ResultData<T> success() {
        return new ResultData<T>(StatusConstant.RESULT_SUCCESS_CODE, StatusConstant.RESULT_SUCCESS_MSG, null);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<T>(StatusConstant.RESULT_SUCCESS_CODE, StatusConstant.RESULT_SUCCESS_MSG, data);
    }

    public static <T> ResultData<T> fail() {
        return new ResultData<T>(StatusConstant.RESULT_FAIL_CODE, StatusConstant.RESULT_FAIL_MSG, null);
    }

    public static <T> ResultData<T> result(String code, String msg) {
        return new ResultData<T>(code, msg, null);
    }

    public static <T> ResultData<T> result(String code, String msg, T data) {
        return new ResultData<T>(code, msg, data);
    }

}
