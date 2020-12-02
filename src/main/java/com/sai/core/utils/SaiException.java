package com.sai.core.utils;

import com.sai.core.constants.enums.ResultCodeEnum;

import java.text.MessageFormat;

public class SaiException extends RuntimeException {

    private String errCode;

    public SaiException() {
        super(MessageFormat.format(ResultCodeEnum.RESULT_FAIL.getMsg(), ""));
        this.errCode = ResultCodeEnum.RESULT_FAIL.getCode();
    }

    public SaiException(ResultCodeEnum resultCodeEnum, String... args) {
        super(MessageFormat.format(resultCodeEnum.getMsg(), args));
        this.errCode = resultCodeEnum.getCode();
    }

    @Override
    public Throwable getCause() {
        return null;
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        return null;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}
