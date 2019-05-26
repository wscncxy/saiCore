package com.sai.core.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sai.core.constants.Constants;
import com.sai.core.dto.ResultCode;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class PageServiceUtil {

    public static <T> ResultCode add(T data, PageMapper pageMapper) {
        ResultCode validResult = ValidateUtil.valid(data);
        if (!validResult.isSuccess()) {
            return validResult;
        }
        int row = pageMapper.insert(data);
        if (row == 0) {
            return ResultCode.fail("没有添加数据");
        }
        return ResultCode.success();
    }

    public static <T> ResultCode update(T data, PageMapper pageMapper) {
        ResultCode validResult = ValidateUtil.valid(data);
        if (!validResult.isSuccess()) {
            return validResult;
        }
        int row = pageMapper.update(data);
        if (row == 0) {
            return ResultCode.fail("没有更新数据");
        }
        return ResultCode.success();
    }

    public static <T> ResultCode getById(BigDecimal id, PageMapper<T> pageMapper) {
        if (id == null) {
            return ResultCode.fail("id不能为空");
        }
        return ResultCode.success(pageMapper.getById(id));
    }

    public static List select4Page(Map<String, Object> param, PageMapper pageMapper) {
        return select4Page(param, pageMapper, null);
    }

    public static List select4Page(Map<String, Object> param, PageMapper pageMapper, String assembledMethodName) {
        List selectResultList = pageMapper.select4Page(param);
        if (StringUtil.isEmpty(assembledMethodName)) {
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
        Page page = PageHelper.getLocalPage();
        if (page != null) {
            page.clear();
            page.addAll(selectResultList);
            return page;
        }
        return selectResultList;
    }
}
