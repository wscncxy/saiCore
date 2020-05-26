package com.sai.core.utils.es;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import java.util.*;

/**
 * 类ElasticsearchQueryBase.java的实现描述：ElasticsearchQueryBase
 *
 * @author sai
 * @date 2019/07/05
 */
public class ElasticsearchQueryBase extends ElasticsearchDocument {

    @Setter
    @Getter
    protected transient int pageNum = -1;
    /**
     * pageSize
     */
    @Setter
    @Getter
    protected transient int pageSize = 10;
    /**
     * notIn
     */
    @Getter
    protected transient Map<String, Collection> notIn = new HashMap<>();
    /**
     * in
     */
    @Getter
    protected transient Map<String, Collection> in = new HashMap<>();
    /**
     * term
     */
    @Getter
    protected transient Map<String, Object> term = new HashMap<>();
    /**
     * term
     */
    @Getter
    protected transient Map<String, Object> notTerm = new HashMap<>();
    /**
     * rang
     */
    @Getter
    protected transient Map<String, QueryRang> rang = new HashMap<>();
    /**
     * fieldSort
     */
    @Getter
    protected transient List<FieldSortBuilder> fieldSort = new ArrayList<>();
    /**
     * fieldSortMap
     */
    @Getter
    protected transient Map<String, Object> fieldSortMap = new HashMap<>();
    /**
     * scriptSort
     */
    @Getter
    protected transient List<ScriptSortBuilder> scriptSort = new ArrayList<>();
    /**
     * 聚合取Count
     */
    @Getter
    protected transient List<String> countFieldList = new ArrayList<>();

    /**
     * scrollTimeInMillis
     */
    @Setter
    @Getter
    protected transient Long scrollTimeInMillis;
    /**
     * scrollId
     */
    @Setter
    @Getter
    protected transient String scrollId;

    /**
     * aggregations size
     */
    @Setter
    @Getter
    protected transient Integer aggrSize = 1000;

    @Getter
    protected transient List<String> fields = new ArrayList<>();

    /**
     * notIn
     *
     * @param k
     * @param values
     * @return
     */
    public ElasticsearchQueryBase notIn(String k, Collection values) {
        notIn.put(k, values);
        return this;
    }

    /**
     * in
     *
     * @param k
     * @param values
     * @return
     */
    public ElasticsearchQueryBase in(String k, Collection values) {
        in.put(k, values);
        return this;
    }

    /**
     * k=vaule
     *
     * @param k      字段
     * @param values 值
     * @return
     */
    public ElasticsearchQueryBase term(String k, Object values) {
        term.put(k, values);
        return this;
    }

    /**
     * k=vaule
     *
     * @param k      字段
     * @param values 值
     * @return
     */
    public ElasticsearchQueryBase notTerm(String k, Object values) {
        notTerm.put(k, values);
        return this;
    }

    /**
     * lt
     *
     * @param k
     * @param values
     * @return
     */
    public ElasticsearchQueryBase lt(String k, Number values) {
        QueryRang queryRang = rang.get(k);
        if (queryRang == null) {
            queryRang = new QueryRang();
        }
        queryRang.setLt(values);
        rang.put(k, queryRang);
        return this;
    }

    /**
     * lte
     *
     * @param k
     * @param values
     * @return
     */
    public ElasticsearchQueryBase lte(String k, Number values) {
        QueryRang queryRang = rang.get(k);
        if (queryRang == null) {
            queryRang = new QueryRang();
        }
        queryRang.setLte(values);
        rang.put(k, queryRang);
        return this;
    }

    /**
     * gt
     *
     * @param k
     * @param values
     * @return
     */
    public ElasticsearchQueryBase gt(String k, Object values) {
        QueryRang queryRang = rang.get(k);
        if (queryRang == null) {
            queryRang = new QueryRang();
        }
        queryRang.setGt(values);
        rang.put(k, queryRang);
        return this;
    }

    /**
     * gte
     *
     * @param k
     * @param values
     * @return
     */
    public ElasticsearchQueryBase gte(String k, Number values) {
        QueryRang queryRang = rang.get(k);
        if (queryRang == null) {
            queryRang = new QueryRang();
        }
        queryRang.setGte(values);
        rang.put(k, queryRang);
        return this;
    }

    /**
     * 排序，按加入字段先后顺序指定排序规则 EX：Mysql：order by userGroup DESC，userId ASC ES：sort("userGroup",SortOrder.DESC)
     * sort("userId",SortOrder.ASC)
     *
     * @param field
     * @param sortOrder
     * @return
     */
    public ElasticsearchQueryBase sort(String field, SortOrder sortOrder) {
        fieldSort.add(SortBuilders.fieldSort(field).order(sortOrder));
        fieldSortMap.put(field, sortOrder == SortOrder.DESC ? "desc" : "asc");
        return this;
    }

    /**
     * sort
     *
     * @param script
     * @param isNumber
     * @return
     */
    public ElasticsearchQueryBase sort(Script script, boolean isNumber) {
        scriptSort.add(SortBuilders.scriptSort(script, isNumber ? ScriptSortBuilder.ScriptSortType.NUMBER : ScriptSortBuilder.ScriptSortType.STRING));
        return this;
    }

    /**
     * randomSort
     *
     * @param field
     * @return
     */
    public ElasticsearchQueryBase randomSort(String field) {
        return randomSort(field, SortOrder.ASC, true);
    }

    /**
     * randomSort
     *
     * @param field
     * @param isNumber
     * @return
     */
    public ElasticsearchQueryBase randomSort(String field, boolean isNumber) {
        return randomSort(field, SortOrder.ASC, isNumber);
    }

    /**
     * randomSort
     *
     * @param field
     * @param sortOrder
     * @param isNumber
     * @return
     */
    public ElasticsearchQueryBase randomSort(String field, SortOrder sortOrder, boolean isNumber) {
        Map randomSort = new HashMap();
        randomSort.put("script", "Math.random()");
        randomSort.put("type", isNumber ? "number" : "string");
        randomSort.put("order", sortOrder == SortOrder.DESC ? "desc" : "asc");
        fieldSortMap.put("_script", randomSort);
        Script script = new Script("Math.random()");
        scriptSort.add(SortBuilders.scriptSort(script, isNumber ? ScriptSortBuilder.ScriptSortType.NUMBER : ScriptSortBuilder.ScriptSortType.STRING).order(sortOrder));
        return this;
    }

    @Setter
    private List<String> idList = new ArrayList<>();

    public void addId(String id) {
        idList.add(id);
    }

    /**
     * setFieldList
     *
     * @param fields
     */
    public void setFieldList(String[] fields) {
        this.fields.addAll(Arrays.asList(fields));
    }

    /**
     * setFieldList
     *
     * @param fields
     */
    public void setFieldList(List fields) {
        this.fields.addAll(fields);
    }

    /**
     *
     */
    public void setCountFieldList(String[] fields) {
        this.countFieldList.addAll(Arrays.asList(fields));
    }

    /**
     * QueryRang
     */
    @Data
    public class QueryRang {
        private Object lt;
        private Object gt;
        private Object lte;
        private Object gte;
    }

}
