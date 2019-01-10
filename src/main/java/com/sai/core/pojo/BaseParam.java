package com.sai.core.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ZhouXiang sai on 2017/7/28 0028 17:10.
 */
public class BaseParam implements Serializable {
    private BigDecimal id;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }
}
