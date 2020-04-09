package com.sai.core;

import com.sai.core.annotation.AssembledDataMethod;
import com.sai.core.constants.Constants;
import com.sai.core.pojo.ScannerSourceInfo;
import com.sai.core.utils.ClassScanUtil;
import com.sai.core.utils.StringUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Method;
import java.util.List;

@Slf4j
public class InitCore {
    @Getter
    private static volatile boolean initOk = false;

    public static void init(String rootPackageName) {
        if (StringUtil.isEmpty(rootPackageName)) {
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
