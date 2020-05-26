package com.sai.core.utils.es;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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

    @Getter
    private List documents;


    public void addDocument(Object obj){
        if(documents == null){
            synchronized (this){
                if(documents == null){
                    documents= new ArrayList();
                }
            }
        }
        documents.add(obj);
    }

    /**
     * build
     * 
     * @return
     */
    public Object build(){
        return null;
    }
}
