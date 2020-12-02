package com.sai.core.utils.es;

import java.util.List;

/**
 * 类ScrollPage.java的实现描述：ScrollPage
 * 
 * @author Jianglong Li
 * @date 2019/07/05
 */
public class ScrollPage<T> {
    /**
     * @param elasticsearchTypeEnum
     * @param scrollId
     * @param scrollTimeInMillis
     * @param cla
     */
    public ScrollPage(ElasticsearchTypeBaseEnum elasticsearchTypeEnum, String scrollId, Long scrollTimeInMillis,
                      Class<T> cla) {
        this.elasticsearchTypeEnum = elasticsearchTypeEnum;
        this.scrollId = scrollId;
        this.scrollTimeInMillis = scrollTimeInMillis;
        this.cla = cla;
    }

    /**
     * elasticsearchType
     */
    protected transient ElasticsearchTypeBaseEnum elasticsearchTypeEnum;
    /**
     * scrollId
     */
    private String scrollId;
    /**
     * scrollTimeInMillis
     */
    private Long scrollTimeInMillis;
    /**
     * cla
     */
    private Class cla;
    /**
     * dataList
     */
    private List<T> dataList;

    private Integer size;
    /**
     * totalCount
     */
    private long                                  totalCount;
    /**
     * readCount
     */
    private long                                  readCount;

    public Integer getSize() {
        return dataList == null ? 0 : dataList.size();
    }

    public ElasticsearchTypeBaseEnum getElasticsearchTypeEnum() {
        return elasticsearchTypeEnum;
    }

    public void setElasticsearchTypeEnum(ElasticsearchTypeBaseEnum elasticsearchTypeEnum) {
        this.elasticsearchTypeEnum = elasticsearchTypeEnum;
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public Long getScrollTimeInMillis() {
        return scrollTimeInMillis;
    }

    public void setScrollTimeInMillis(Long scrollTimeInMillis) {
        this.scrollTimeInMillis = scrollTimeInMillis;
    }

    public Class getCla() {
        return cla;
    }

    public void setCla(Class cla) {
        this.cla = cla;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getReadCount() {
        return readCount;
    }

    public void setReadCount(long readCount) {
        this.readCount = readCount;
    }
}
