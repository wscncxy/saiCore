package com.sai.core.utils.es;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 类ElasticsearchDocument.java的实现描述：ElasticsearchDocument
 * 
 * @author sai
 * @date 2019/07/05
 */
@Data
public class ElasticsearchDocument {
    /**
     * id
     */
    protected String id;
    /**
     * elasticsearchType
     */
    @JsonIgnore
    protected transient ElasticsearchTypeBaseEnum elasticsearchTypeEnum;
    /**
     * cla
     */
    protected Class cla;
    /**
     * build
     * 
     * @return
     */
    public Object build(){
        return null;
    }
}
