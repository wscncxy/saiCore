package com.sai.core.dto;

import com.sai.core.constants.StatusConstant;

public class ResultCode<T> {

    /**
     * Created by ZhouXiang on 2017/8/15 0015 13:38.
     */

    private String code;
    private String msg;
    private T data;

    public ResultCode() {
    }

    public ResultCode(String code, String msg, T data) {
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

    public boolean isSuccess() {
        return org.apache.commons.lang3.StringUtils.equals(StatusConstant.RESULT_SUCCESS_CODE, code);
    }

    public final static <T> ResultCode<T> fail(String msg) {
        return new ResultCode<T>(StatusConstant.RESULT_FAIL_CODE, msg, null);
    }

    public final static <T> ResultCode<T> fail(String code, String msg) {
        return new ResultCode<T>(code, msg, null);
    }

    public final static <T> ResultCode<T> success(String msg, T data) {
        return new ResultCode<T>(StatusConstant.RESULT_SUCCESS_CODE, msg, data);
    }

    public final static <T> ResultCode<T> success() {
        return new ResultCode<T>(StatusConstant.RESULT_SUCCESS_CODE, StatusConstant.RESULT_SUCCESS_MSG, null);
    }

    public final static <T> ResultCode<T> success(T data) {
        return new ResultCode<T>(StatusConstant.RESULT_SUCCESS_CODE, StatusConstant.RESULT_SUCCESS_MSG, data);
    }

    public final static <T> ResultCode<T> successMsg(String msg) {
        return new ResultCode<T>(StatusConstant.RESULT_SUCCESS_CODE, msg, null);
    }

    public final static <T> ResultCode<T> fail() {
        return new ResultCode<T>(StatusConstant.RESULT_FAIL_CODE, StatusConstant.RESULT_FAIL_MSG, null);
    }

    public final static <T> ResultCode<T> result(String code, String msg) {
        return new ResultCode<T>(code, msg, null);
    }

    public final static <T> ResultCode<T> result(String code, String msg, T data) {
        return new ResultCode<T>(code, msg, data);
    }

}
