package com.sai.core.utils;

import com.sai.core.constants.Constants;
import com.sai.core.dto.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PageServiceUtil {

    private static final Logger log = LoggerFactory.getLogger(PageServiceUtil.class);
    public static <T> ResultData add(T data, PageMapper pageMapper) {
        ValidateUtil.valid(data);

        int row = pageMapper.insert(data);
        if (row == 0) {
            return ResultData.fail("没有添加数据");
        }
        return ResultData.success();
    }

    public static <T> ResultData update(T data, PageMapper pageMapper) {
        ValidateUtil.valid(data);

        int row = pageMapper.update(data);
        if (row == 0) {
            return ResultData.fail("没有更新数据");
        }
        return ResultData.success();
    }

    public static <T> ResultData getById(BigDecimal id, PageMapper<T> pageMapper) {
        if (id == null) {
            return ResultData.fail("id不能为空");
        }
        return ResultData.success(pageMapper.getById(id));
    }

    public static List select4Page(Map<String, Object> param, PageMapper pageMapper) {
        return select4Page(param, pageMapper, null);
    }

    public static List select4Page(Map<String, Object> param, PageMapper pageMapper, String assembledMethodName) {
        List selectResultList = pageMapper.select4Page(param);
        if (SaiStringUtils.isEmpty(assembledMethodName)) {
            Method assembledMethod = Constants.ASSEMBLED_DATA_METHOD_MAP.get(assembledMethodName);
            if (assembledMethod != null) {
                Object cla = ClassScanUtil.getClassInstance(assembledMethod.getDeclaringClass());
                try {
                    List assembledList = (List) assembledMethod.invoke(cla, selectResultList);
                    selectResultList = assembledList;
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }

        return selectResultList;
    }
}
