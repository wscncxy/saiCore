package com.sai.core.constants;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Constants {
    public static final String SYMBOL_POINT = ".";
    public static final String SYMBOL_LEFT_SLASH = "/";
    public static final String SYMBOL_RIGHT_SLASH = "\\";
    public static final String SYMBOL_DOUBLE_STAR = "**";
    public static final String SYMBOL_SINGLE_STAR = "*";

    public static final Long TIME_ONE_DAY_SECOND = 60 * 60 * 24L;

    public static final String SAI_PROGRAM_OPENAPI = "openApi";
    public static final String SAI_PROGRAM_SHOPPING = "shoping";
    public static final String SAI_PROGRAM_AREA = "area";
    public static final String SAI_PROGRAM_MANAGE = "management";

    public static final Byte SAI_PROGRAM_INDEX_AREA = 1;
    public static final Byte SAI_PROGRAM_INDEX_OPENAPI = 2;
    public static final Byte SAI_PROGRAM_INDEX_MANAGE = 3;
    public static final Byte SAI_PROGRAM_INDEX_SHOPPING = 4;

    public static Map<Byte, String> SAI_PROGRAM_INDEX_MAP = new HashMap<>();
    public static Map<String, Byte> SAI_PROGRAM_MAP = new HashMap<>();

    static {
        SAI_PROGRAM_INDEX_MAP.put(SAI_PROGRAM_INDEX_OPENAPI, SAI_PROGRAM_OPENAPI);
        SAI_PROGRAM_INDEX_MAP.put(SAI_PROGRAM_INDEX_SHOPPING, SAI_PROGRAM_SHOPPING);
        SAI_PROGRAM_INDEX_MAP.put(SAI_PROGRAM_INDEX_AREA, SAI_PROGRAM_AREA);
        SAI_PROGRAM_INDEX_MAP.put(SAI_PROGRAM_INDEX_MANAGE, SAI_PROGRAM_MANAGE);

        Iterator<Map.Entry<Byte, String>> iterator = SAI_PROGRAM_INDEX_MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Byte, String> entry = iterator.next();
            SAI_PROGRAM_MAP.put(entry.getValue(), entry.getKey());
        }
    }
}
