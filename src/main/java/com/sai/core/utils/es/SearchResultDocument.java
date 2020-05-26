package com.sai.core.utils.es;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 类XSearchResultDocument.java的实现描述：XSearchResultDocument
 * 
 * @author zhouxiang001
 * @date 2019/07/05
 */
@Data
public class SearchResultDocument<T> {
    /**
     * a
     */
    private String success;
    /**
     * a
     */
    private String errorMsg;
    /**
     * a
     */
    private Long totalHits;
    /**
     * a
     */
    private String scrollId;
    /**
     * a
     */
    private List<T> hitsData;

    private Integer size;

    private Map<String,ElasticsearchAggregations> aggregations;

    public Integer getSize() {
        return hitsData == null ? 0 : hitsData.size();
    }
}
