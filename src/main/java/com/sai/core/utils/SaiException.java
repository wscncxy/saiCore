package com.sai.core.utils;

import com.sai.core.constants.enums.ResultCodeEnum;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

@Setter
@Getter
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
}
