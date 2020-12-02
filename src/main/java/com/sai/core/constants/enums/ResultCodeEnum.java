package com.sai.core.constants.enums;

public enum ResultCodeEnum {


    RESULT_FAIL_UNKNOWN("9999","未知错误"),
    RESULT_FAIL("9999","{0}"),
    RESULT_DATA_IS_BLANK("10001","{0}不能为空"),
    RESULT_DATA_TYPE_IS_ERR("10002","{0}类型错误"),
    RESULT_DATA_TYPE_NO_SUPPORT("10002","{0}不支持"),
    RESULT_DATA_IS_NOT_NUM("10003","{0}非数字"),
    RESULT_DATA_VAL_GT_MAX("10004","{0} 不能大于 {1}"),
    RESULT_DATA_VAL_LT_MIN("10004","{0} 不能小于 {1}"),
    RESULT_DATA_LEN_MAX("10004","{0} 长度不能大于 {1}"),
    RESULT_DATA_LEN_MIN("10004","{0} 长度不能小于 {1}"),
    ;
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
