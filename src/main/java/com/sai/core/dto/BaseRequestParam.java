package com.sai.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by zhouXiang sai on 2017/7/28 0028.
 */
public class BaseRequestParam implements Serializable{
    private BigDecimal id;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }
}
