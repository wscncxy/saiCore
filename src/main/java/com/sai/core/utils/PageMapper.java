package com.sai.core.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PageMapper<T> {
    List select4Page(Map<String, Object> param);
    int insert(T addInfo);
    int update(T updateInfo);
    T getById(BigDecimal id);
}
