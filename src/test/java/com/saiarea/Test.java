package com.saiarea;

import com.sai.core.utils.BeanUtils;
import com.sai.core.utils.ClassScanUtil;

/**
 * Created by ZhouXiang on 2018/3/1 0001 11:32.
 */
public class Test {
    public static void main(String[] arg){
        ClassScanUtil.scanner(BeanUtils.class.getPackage().getName(),false);
    }
}
