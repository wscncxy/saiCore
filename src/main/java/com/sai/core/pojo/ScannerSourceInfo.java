package com.sai.core.pojo;

import lombok.Data;

import java.lang.reflect.Method;

@Data
public class ScannerSourceInfo {
    private Class cla;
    private Method[] method;

}
