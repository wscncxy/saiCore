package com.sai.core.utils;

import com.sai.core.dto.ResultCode;

import java.util.List;
import java.util.Map;

public interface PageMapper<T> {
    List select4Page(Map<String, Object> param);
    int insert(T addInfo);
    int update(T updateInfo);
    T getById(Long id);
}
