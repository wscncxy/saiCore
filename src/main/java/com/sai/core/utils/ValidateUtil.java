package com.sai.core.utils;

import com.sai.core.dto.ResultCode;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class ValidateUtil {

    public static <T> ResultCode<String> validBeforeAdd(T addInfo) {
        if (addInfo == null) {
            return ResultCode.fail("内容为空");
        }

        Field[] allField = addInfo.getClass().getFields();
        for (Field field : allField) {
            DataValid dataValid = field.getAnnotation(DataValid.class);
            if (dataValid == null) {
                continue;
            }

            try {
                Object fieldVal = field.get(addInfo);
                String fieldName = dataValid.name() == null ? field.getName() : dataValid.name();
                if (dataValid.noNull() && fieldVal == null) {
                    return ResultCode.fail(fieldName + "内容不能为空");
                }


                BigDecimal fieldValBig = new BigDecimal(fieldVal.toString());
                String maxVal = dataValid.maxVal();
                if (StringUtil.isNotEmpty(maxVal)) {
                    int r = fieldValBig.compareTo(new BigDecimal(maxVal));
                    if (r == 1) {
                        return ResultCode.fail(fieldName + "不能大于" + maxVal);
                    }
                }
                String minVal = dataValid.minVal();
                if (StringUtil.isNotEmpty(minVal)) {
                    int r = fieldValBig.compareTo(new BigDecimal(minVal));
                    if (r == -1) {
                        return ResultCode.fail(fieldName + "不能小于" + minVal);
                    }
                }

                Number fieldLen = getFieldLen(fieldVal);
                long maxLen = dataValid.maxLen();
                if (maxLen > -1) {
                    if (fieldLen.longValue() > maxLen) {
                        return ResultCode.fail(fieldName + "长度不能大于" + maxLen);
                    }
                }

                long minLen = dataValid.minLen();
                if (minLen > -1) {
                    if (fieldLen.longValue() < minLen) {
                        return ResultCode.fail(fieldName + "长度不能小于" + minLen);
                    }
                }

                Class valildEnum = dataValid.valildEnum();
                if (valildEnum != null && valildEnum.isEnum()) {

                }

                Class<Validate> valid = dataValid.valild();
                if (valid != null && valid.isLocalClass()) {
                    Object valiObj = valid.newInstance();
                    ResultCode resultCode = (ResultCode) valid.getMethod("valid").invoke(valiObj, fieldName, fieldVal);
                    return resultCode;
                }
            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }
        }

        return ResultCode.success();
    }

    private static Number getFieldLen(Object fieldVal) {
        if (fieldVal == null) {
            return 0;
        }
        try {
            if (fieldVal instanceof List || fieldVal instanceof Set || fieldVal instanceof Map) {
                return (Integer) fieldVal.getClass().getMethod("size").invoke(fieldVal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fieldVal.toString().length();
    }
}
