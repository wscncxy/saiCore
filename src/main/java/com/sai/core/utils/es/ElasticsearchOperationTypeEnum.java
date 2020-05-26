package com.sai.core.utils.es;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 类XSearchOperationTypeEnum.java的实现描述：XSearchOperationTypeEnum
 * 
 * @author sai
 */
@Getter
@AllArgsConstructor
public enum ElasticsearchOperationTypeEnum {
    SEARCH("search", "/search/", ""), GET("personalCollectionSucNofaceRecognitionSuc", "/get/", ""),
    SCROLL("scroll", "/scroll/", "滚动查询"), INSERT("INSERT", "/insert/", "不存在则插入"),
    UPDATE("UPDATE", "/update/", "不存在则插入"), DELETE("DELETE", "/delete/", ""),
    CUSTOM_SCORE_SEARCH("CUSTOM_SCORE_SEARCH", "/customScoreSearch/", "自定义排序查询记录API"),
    BULK_INSERT("BULK_INSERT", "/bulkInsert/", ""), BULK_UPDATE("BULK_UPDATE", "/bulkUpdate/", "")
    , BULK_DELETE("BULK_DELETE", "/bulkDelete/", "");
    /**
     * code
     */
    private String code;
    /**
     * uri
     */
    private String uri;
    /**
     * message
     */
    private String message;

    /**
     * getByCode
     * 
     * @param code
     * @return
     */
    public static final ElasticsearchOperationTypeEnum getByCode(String code) {
        for (ElasticsearchOperationTypeEnum tmpEnum : values()) {
            if (StringUtils.equalsIgnoreCase(code, tmpEnum.getCode())) {
                return tmpEnum;
            }
        }
        return null;
    }

}
