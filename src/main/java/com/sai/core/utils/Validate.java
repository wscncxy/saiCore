package com.sai.core.utils;

import com.sai.core.dto.ResultCode;

public interface Validate {
    ResultCode valid(String key,Object val);
}
