package com.sai.core.dto;

import com.sai.core.constants.StatusConstant;
import com.sai.core.utils.SaiStringUtils;

import java.beans.Transient;
import java.util.HashMap;
import java.util.Map;

public class HttpResultData extends ResultData<Object> {

    /**
     * Created by ZhouXiang on 2026/2/15 13:38.
     */

    private Map<String, String> headers;
    private int httpCode;

    private static final int HTTP_SUCCESS = 200;

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public HttpResultData() {
    }

    public HttpResultData(String code,
                          String msg,
                          int httpCode,
                          Map<String, String> headers,
                          Object body) {
        super(code, msg, body);
        setHttpCode(httpCode);
        setHeaders(headers);
    }

    @Transient
    public boolean isSuccess() {
        return SaiStringUtils.equals(StatusConstant.RESULT_SUCCESS_CODE, getCode());
    }

    public static HttpResultData successWithHeaders(Object data, Map<String, String> headers) {
        return successWithHeaders(data, HTTP_SUCCESS, headers);
    }

    public static HttpResultData successWithHeaders(Object body, int httpCode, Map<String, String> headers) {
        return new HttpResultData(
                StatusConstant.RESULT_SUCCESS_CODE,
                StatusConstant.RESULT_SUCCESS_MSG,
                httpCode,
                headers,
                body
        );
    }

    public static HttpResultData successWithHeaders(String msg, Object body, HashMap<String, String> headers) {
        return new HttpResultData(
                StatusConstant.RESULT_SUCCESS_CODE,
                msg,
                HTTP_SUCCESS,
                headers,
                body);
    }

}
