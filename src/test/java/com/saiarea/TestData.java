package com.saiarea;

import com.sai.core.annotation.DataValid;

import java.math.BigDecimal;

public class TestData {
    @DataValid(notBlank = true)
    private BigDecimal id;
    @DataValid(maxLen = 10)
    private String name;
    @DataValid(minLen = 3)
    private String desc;
    @DataValid(maxVal = "200",minVal = "18")
    private int age;
    @DataValid(minVal = "10")
    private int accountCount;
}
