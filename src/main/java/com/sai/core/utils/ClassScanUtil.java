package com.sai.core.utils;

import com.sai.core.pojo.ScannerSourceInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ClassScanUtil {
    private static ClassLoader defaultClassLoader = Thread.currentThread().getContextClassLoader();

    public static List<ScannerSourceInfo> scanner(String packageName, boolean needScanSub) {
        List<ScannerSourceInfo> allClassList = new ArrayList<>();
        if (StringUtil.isEmpty(packageName)) {
            return allClassList;
        }
        URL packageURL = defaultClassLoader.getResource("" + packageName.replaceAll("\\.", "/"));
        File file = new File(packageURL.getFile());
        if (file.exists()) {
            for (File subFile : file.listFiles()) {
                if (subFile.isDirectory()) {
                    if (needScanSub) {
                        allClassList.addAll(scanner(packageName + "." + subFile.getName(), needScanSub));
                    }
                } else {
                    String subFileName = subFile.getName();
                    String className = packageName + "." + subFileName.replace(".class", "");
                    try {
                        ScannerSourceInfo scannerSourceInfo = new ScannerSourceInfo();
                        Class cla = Class.forName(className);
                        scannerSourceInfo.setCla(cla);
                        scannerSourceInfo.setMethods(cla.getDeclaredMethods());
                        allClassList.add(scannerSourceInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        log.info(allClassList.size() + "");

        return allClassList;
    }

    public static Object getClassInstance(Class cla) {
        if (cla == null) {
            return null;
        }
        try {
            return cla.newInstance();
        } catch (Exception e) {

        }
        return null;
    }
}
