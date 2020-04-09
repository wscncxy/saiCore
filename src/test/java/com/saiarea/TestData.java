package com.saiarea;

import com.sai.core.annotation.DataValid;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TestData {
    @DataValid(noNull = true)
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
