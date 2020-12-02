package com.sai.core.pojo;

import java.lang.reflect.Method;

public class ScannerSourceInfo {
    private Class cla;
    private Method[] methods;

    public Class getCla() {
        return cla;
    }

    public void setCla(Class cla) {
        this.cla = cla;
    }

    public Method[] getMethods() {
        return methods;
    }

    public void setMethods(Method[] methods) {
        this.methods = methods;
    }
}
