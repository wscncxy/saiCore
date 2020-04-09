package com.sai.core.utils;

import com.sai.core.annotation.DataValid;
import com.sai.core.constants.enums.ResultCodeEnum;
import com.sai.core.dto.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class ValidateUtil {

    public static void valid(Object data) {
        try {
            if (data == null) {
                throw new SaiException();
            }

            Field[] allField = data.getClass().getDeclaredFields();
            for (Field field : allField) {
                DataValid dataValid = field.getAnnotation(DataValid.class);
                if (dataValid == null) {
                    continue;
                }
                field.setAccessible(true);
                Object fieldVal = field.get(data);
                String fieldName = StringUtil.isEmpty(dataValid.name()) ? field.getName() : dataValid.name();

                if (dataValid.notBlank()) {
                    validNotBlank(fieldVal, fieldName);
                }

                String maxVal = dataValid.maxVal();
                if (StringUtil.isNotEmpty(maxVal)) {
                    validNum(fieldVal, maxVal, fieldName, true);
                }
                String minVal = dataValid.minVal();
                if (StringUtil.isNotEmpty(minVal)) {
                    validNum(fieldVal, minVal, fieldName, false);
                }

                Integer maxLen = dataValid.maxLen();
                if (maxLen > -1) {
                    validLen(field, maxLen, fieldName, true);
                }

                int minLen = dataValid.minLen();
                if (minLen > -1) {
                    validLen(field, maxLen, fieldName, false);
                }

            }
            if (data instanceof Validate) {
                data.getClass().getMethod("valid").invoke(data);
            }
        } catch (SaiException e){
            throw new SaiException(ResultCodeEnum.RESULT_FAIL_UNKNOWN);
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void validNotBlank(Object data, String fieldName) {
        if (data == null) {
            throw new SaiException(ResultCodeEnum.RESULT_DATA_IS_BLANK, fieldName);
        }

        if (data instanceof List && CollectionUtils.isEmpty((List) data)) {
            throw new SaiException(ResultCodeEnum.RESULT_DATA_IS_BLANK, fieldName);
        }

        if (data instanceof String && StringUtil.isBlank((String) data)) {
            throw new SaiException(ResultCodeEnum.RESULT_DATA_IS_BLANK, fieldName);
        }

        if (data instanceof Map && MapUtils.isEmpty((Map) data)) {
            throw new SaiException(ResultCodeEnum.RESULT_DATA_IS_BLANK, fieldName);
        }
    }

    public static void validNum(Object data, String val, String fieldName, boolean max) {
        if (data == null) {
            throw new SaiException(ResultCodeEnum.RESULT_DATA_IS_BLANK, fieldName);
        }

        if (!(data instanceof Number)) {
            throw new SaiException(ResultCodeEnum.RESULT_DATA_TYPE_IS_ERR, fieldName);
        }

        if (NumberUtils.isCreatable(val)) {
            throw new SaiException(ResultCodeEnum.RESULT_DATA_IS_NOT_NUM, "maxVal");
        }

        BigDecimal num = new BigDecimal(String.valueOf(data));
        BigDecimal point = new BigDecimal(String.valueOf(val));
        if (num.compareTo(point) == (max ? 1 : -1)) {
            throw new SaiException(max ? ResultCodeEnum.RESULT_DATA_VAL_GT_MAX : ResultCodeEnum.RESULT_DATA_VAL_LT_MIN, fieldName, val);
        }
    }

    public static void validLen(Object data, Integer val, String fieldName, boolean max) {
        if (data == null) {
            throw new SaiException(ResultCodeEnum.RESULT_DATA_IS_BLANK, fieldName);
        }
        Integer len;
        if (data instanceof String) {
            len = ((String) data).length();
        } else {
            len = CollectionUtils.size(data);
        }
        if (val.compareTo(len) == (max ? 1 : -1)) {
            throw new SaiException(max ? ResultCodeEnum.RESULT_DATA_LEN_MAX : ResultCodeEnum.RESULT_DATA_LEN_MIN, fieldName, val + "");
        }
    }
}
