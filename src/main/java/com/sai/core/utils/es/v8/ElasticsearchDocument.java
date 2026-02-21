package com.sai.core.utils.es.v8;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sai.core.utils.es.ElasticsearchTypeBaseEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 类ElasticsearchDocument.java的实现描述：ElasticsearchDocument
 * 
 * @author sai
 * @date 2019/07/05
 */
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ElasticsearchTypeBaseEnum getElasticsearchTypeEnum() {
        return elasticsearchTypeEnum;
    }

    public void setElasticsearchTypeEnum(ElasticsearchTypeBaseEnum elasticsearchTypeEnum) {
        this.elasticsearchTypeEnum = elasticsearchTypeEnum;
    }

    public Class getCla() {
        return cla;
    }

    public void setCla(Class cla) {
        this.cla = cla;
    }

    public List getDocuments() {
        return documents;
    }

    public void setDocuments(List documents) {
        this.documents = documents;
    }
}
