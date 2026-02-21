package com.sai.core.utils.es.v8;


import java.util.List;
import java.util.Map;

/**
 * 类XSearchResultDocument.java的实现描述：XSearchResultDocument
 * 
 * @author zhouxiang001
 * @date 2019/07/05
 */
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

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Long getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(Long totalHits) {
        this.totalHits = totalHits;
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public List<T> getHitsData() {
        return hitsData;
    }

    public void setHitsData(List<T> hitsData) {
        this.hitsData = hitsData;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Map<String, ElasticsearchAggregations> getAggregations() {
        return aggregations;
    }

    public void setAggregations(Map<String, ElasticsearchAggregations> aggregations) {
        this.aggregations = aggregations;
    }
}
