package com.sai.core.constents;

/**
 * Created by ZhouXiang on 2017/7/28 0028 16:56.
 */
public class StatusContent {
    /**
     * 处理成功状态码
     **/
    public static final String RESULT_SUCCESS_CODE = "0000";
    /**
     * 处理成功返回信息
     **/
    public static final String RESULT_SUCCESS_MSG = "操作成功";
    public static final String RESULT_SUCCESS_MSG_DELSUC = "删除成功";
    public static final String RESULT_SUCCESS_MSG_ADDSUC = "添加成功";
    public static final String RESULT_SUCCESS_MSG_UPLOADPICSUC = "添加成功";

    /**
     * 处理失败状态码
     **/
    public static final String RESULT_FAIL_CODE = "9999";
    public static final String RESULT_FAIL_MSG = "操作成功";
    public static final String RESULT_FAIL_MSG_DELFAIL = "删除失败";
    public static final String RESULT_FAIL_MSG_ADDFAIL = "添加失败";
    public static final String RESULT_FAIL_MSG_UPLOADPICFAIL = "添加失败";

    public static final String RESULT_FAIL_MSG_BINDINGERR = "参数格式有误，请检查";

    public static final Long TIME_DAY = 86400L;


}
