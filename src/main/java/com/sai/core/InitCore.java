package com.sai.core;

import com.sai.core.annotation.AssembledDataMethod;
import com.sai.core.constants.Constants;
import com.sai.core.pojo.ScannerSourceInfo;
import com.sai.core.utils.ClassScanUtil;
import com.sai.core.utils.SaiStringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.List;

public class InitCore {

    private static final Logger log = LoggerFactory.getLogger(InitCore.class);
    private static volatile boolean initOk = false;


    public static boolean isInitOk() {
        return initOk;
    }

    public static void init(String rootPackageName) {
        if (SaiStringUtils.isEmpty(rootPackageName)) {
            return;
        }
        List<ScannerSourceInfo> scannerSourceInfoList = ClassScanUtil.scanner(rootPackageName, true);
        if (CollectionUtils.isNotEmpty(scannerSourceInfoList)) {
            for (ScannerSourceInfo scannerSourceInfo : scannerSourceInfoList) {
                initAssembledDataMethod(scannerSourceInfo);
            }
        }
        log.info("核心包初始化完成");
        initOk = true;
    }

    private static void initAssembledDataMethod(ScannerSourceInfo scannerSourceInfo) {
        Method[] methods = scannerSourceInfo.getMethods();
        for (Method method : methods) {
            try {
                AssembledDataMethod assembledDataMethod = method.getAnnotation(AssembledDataMethod.class);
                if (assembledDataMethod != null) {
                    String name = assembledDataMethod.name();
                    if (Constants.ASSEMBLED_DATA_METHOD_MAP.containsKey(name)) {
                        log.error("assembledDataMethod 冲突");
                        return;
                    }
                    Constants.ASSEMBLED_DATA_METHOD_MAP.put(name, method);
                }
            } catch (Exception e) {

            }
        }
    }

}
