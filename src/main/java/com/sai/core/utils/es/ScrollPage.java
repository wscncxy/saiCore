package com.sai.core.utils.es;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

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
    @Getter
    protected transient ElasticsearchTypeBaseEnum elasticsearchTypeEnum;
    /**
     * scrollId
     */
    @Getter
    private String scrollId;
    /**
     * scrollTimeInMillis
     */
    @Getter
    private Long scrollTimeInMillis;
    /**
     * cla
     */
    @Getter
    private Class cla;
    /**
     * dataList
     */
    @Getter
    @Setter
    private List<T> dataList;

    @Setter
    private Integer size;
    /**
     * totalCount
     */
    @Getter
    @Setter
    private long                                  totalCount;
    /**
     * readCount
     */
    @Getter
    @Setter
    private long                                  readCount;

    public Integer getSize() {
        return dataList == null ? 0 : dataList.size();

    }
}
