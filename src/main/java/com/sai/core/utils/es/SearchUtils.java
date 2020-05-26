package com.sai.core.utils.es;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * SearchUtils
 */
@Slf4j
public abstract class SearchUtils<C extends ElasticsearchQueryBase> {

    /**
     * xsearchURL
     */
    @Setter
    private String xsearchURL;

    private Class<C> condition;

    protected SearchUtils(Class<C> condition) {
        this.condition = condition;
    }

    /**
     * 二向箔查询
     *
     * @return
     */
    public SearchResultDocument search(C elasticsearchQueryBase) {
        return doSearch(elasticsearchQueryBase, ElasticsearchOperationTypeEnum.SEARCH);
    }

    private C createC() {
        try {
            Constructor<C> constructor = condition.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /**
     * @param elasticsearchTypeEnum
     * @param id
     * @return
     */
    public SearchResultDocument getById(ElasticsearchTypeBaseEnum elasticsearchTypeEnum, String id, Class cla) {
        C condition = createC();
        condition.addId(id);
        condition.setElasticsearchTypeEnum(elasticsearchTypeEnum);
        condition.setCla(cla);
        return doSearch(condition, ElasticsearchOperationTypeEnum.GET);
    }

    /**
     * @param xsearchQueryCondition
     * @return
     */
    public ScrollPage scroll(C xsearchQueryCondition) {
        ScrollPage scrollPage = null;
        SearchResultDocument resultDocument = doSearch(xsearchQueryCondition, ElasticsearchOperationTypeEnum.SCROLL);
        if (resultDocument != null) {
            scrollPage = new ScrollPage(xsearchQueryCondition.getElasticsearchTypeEnum(), resultDocument.getScrollId(),
                    xsearchQueryCondition.getScrollTimeInMillis(), xsearchQueryCondition.getCla());
            List hitsData = resultDocument.getHitsData();
            scrollPage.setDataList(hitsData);
            scrollPage.setSize(hitsData == null ? 0 : hitsData.size());
            if (hitsData != null) {
                scrollPage.setReadCount(hitsData.size());
            }
        }
        return scrollPage;
    }

    /**
     * @param scrollPage
     * @return
     */
    public ScrollPage scrollNext(ScrollPage scrollPage) {
        if (StringUtils.isBlank(scrollPage.getScrollId())) {
            return null;
        }
        C elasticsearchQueryBase = createC();
        elasticsearchQueryBase.setElasticsearchTypeEnum(scrollPage.elasticsearchTypeEnum);
        elasticsearchQueryBase.setScrollId(scrollPage.getScrollId());
        elasticsearchQueryBase.setScrollTimeInMillis(scrollPage.getScrollTimeInMillis());
        elasticsearchQueryBase.setCla(scrollPage.getCla());
        SearchResultDocument resultDocument = this.doSearch(elasticsearchQueryBase,
                ElasticsearchOperationTypeEnum.SCROLL);
        if (resultDocument != null) {
            List hitsData = resultDocument.getHitsData();
            if (hitsData != null) {
                scrollPage.setReadCount(scrollPage.getReadCount() + hitsData.size());
            }
            scrollPage.setDataList(hitsData);
        } else {
            scrollPage.setDataList(null);
        }
        return scrollPage;
    }


    /**
     * @param elasticsearchTypeEnum
     * @param dataInfo
     * @return
     */
    public SearchResultDocument insert(ElasticsearchTypeBaseEnum elasticsearchTypeEnum,
                                       ElasticsearchDocument dataInfo) {
        ElasticsearchDocument xsearchDocument = new ElasticsearchDocument();
        xsearchDocument.setElasticsearchTypeEnum(elasticsearchTypeEnum);
        xsearchDocument.addData(dataInfo);
        SearchResultDocument searchResultDocument = doSearch(xsearchDocument, ElasticsearchOperationTypeEnum.INSERT);
        log.info("二向箔插入返回结果:{}", JSONObject.toJSONString(searchResultDocument));
        return searchResultDocument;
    }

    /**
     * @param elasticsearchTypeEnum
     * @param dataInfo
     * @return
     */
    public SearchResultDocument update(ElasticsearchTypeBaseEnum elasticsearchTypeEnum,
                                       ElasticsearchDocument dataInfo) {
        XSearchDocument xsearchDocument = new XSearchDocument();
        xsearchDocument.setElasticsearchTypeEnum(elasticsearchTypeEnum);
        xsearchDocument.addData(dataInfo);
        SearchResultDocument searchResultDocument = doSearch(xsearchDocument, ElasticsearchOperationTypeEnum.UPDATE);
        log.info("二向箔更新返回结果:{}", JSONObject.toJSONString(searchResultDocument));
        return searchResultDocument;
    }

    /**
     * @param elasticsearchTypeEnum
     * @param dataList
     * @return
     */
    public <T extends ElasticsearchDocument> SearchResultDocument batchInsert(ElasticsearchTypeBaseEnum elasticsearchTypeEnum,
                                                                              Collection<T> dataList) {
        XSearchDocument xsearchDocument = new XSearchDocument();
        xsearchDocument.setElasticsearchTypeEnum(elasticsearchTypeEnum);
        xsearchDocument.addData(dataList);
        SearchResultDocument searchResultDocument = doSearch(xsearchDocument, ElasticsearchOperationTypeEnum.BULK_INSERT);
        log.info("二向箔批量插入返回结果:{}", JSONObject.toJSONString(searchResultDocument));
        return searchResultDocument;
    }

    /**
     * @param elasticsearchTypeEnum
     * @param dataList
     * @return
     */
    public <T extends ElasticsearchDocument> SearchResultDocument batchUpdate(ElasticsearchTypeBaseEnum elasticsearchTypeEnum,
                                                                              Collection<T> dataList) {
        XSearchDocument xsearchDocument = new XSearchDocument();
        xsearchDocument.setElasticsearchTypeEnum(elasticsearchTypeEnum);
        xsearchDocument.addData(dataList);
        SearchResultDocument searchResultDocument = doSearch(xsearchDocument, ElasticsearchOperationTypeEnum.BULK_UPDATE);
        log.info("二向箔批量更新返回结果:{}", JSONObject.toJSONString(searchResultDocument));
        return searchResultDocument;
    }

    /**
     * @param id
     * @return
     */
    public void deleteById(ElasticsearchTypeBaseEnum elasticsearchTypeEnum, String id) {
        if (StringUtils.isBlank(id)) {
            return;
        }
        List<String> idList = new ArrayList<>();
        idList.add(id);
        C elasticsearchQueryBase = createC();
        elasticsearchQueryBase.setIdList(idList);
        elasticsearchQueryBase.setElasticsearchTypeEnum(elasticsearchTypeEnum);
        SearchResultDocument searchResultDocument = doSearch(xsearchQueryCondition, ElasticsearchOperationTypeEnum.DELETE);
        log.info("单删除结果：{}", JSONObject.toJSONString(searchResultDocument));
    }

    /**
     * @param elasticsearchTypeEnum
     * @param idList
     */
    private Integer delete(ElasticsearchTypeBaseEnum elasticsearchTypeEnum, List<String> idList) {
        if (idList == null || idList.size() == 0) {
            return 0;
        }
        log.info("XSearchUtils deleteByIDList 当前删除总数{}", idList.size());
        XSearchQueryCondition xsearchQueryCondition = new XSearchQueryCondition();
        xsearchQueryCondition.setIdList(idList);
        xsearchQueryCondition.setElasticsearchTypeEnum(elasticsearchTypeEnum);
        SearchResultDocument searchResultDocument = doSearch(xsearchQueryCondition,
                ElasticsearchOperationTypeEnum.BULK_DELETE);
        log.info("XSearchUtils deleteByIDList 单删除结果：{}", JSONObject.toJSONString(searchResultDocument));
        return idList.size();
    }

    /**
     * @param deleteQueryCondition
     * @return
     */
    public void delete(C deleteQueryCondition) {
        deleteQueryCondition.setCla(ElasticsearchDocument.class);
        deleteQueryCondition.setFieldList(new String[]{"id"});
        ScrollPage scrollPage = scroll(deleteQueryCondition);
        ElasticsearchTypeBaseEnum elasticsearchTypeEnum = deleteQueryCondition.getElasticsearchTypeEnum();
        Long delCount = 0L;
        while (scrollPage != null && scrollPage.getSize() > 0) {
            List<ElasticsearchDocument> dataList = scrollPage.getDataList();
            log.info("XSearchUtils delete 批量删除数：{}", CollectionUtils.size(dataList));
            if (dataList == null || dataList.size() == 0) {
                break;
            }
            delCount += dataList.size();
            List<String> ids = new ArrayList<>();
            for (ElasticsearchDocument elasticsearchDocument : dataList) {
                ids.add(elasticsearchDocument.getId());
            }
            delete(elasticsearchTypeEnum, ids);
            scrollPage = scrollPage.next();
        }
        log.info("XSearchUtils delete 此次批量删除总数：{}，删除请求报文：{}", delCount, deleteQueryCondition.build());
    }

    /**
     * @param elasticsearchTypeEnum
     * @return
     */
    public void clean(ElasticsearchTypeBaseEnum elasticsearchTypeEnum) {
        if (elasticsearchTypeEnum == null) {
            return;
        }
        XSearchQueryCondition xsearchQueryCondition = new XSearchQueryCondition();
        xsearchQueryCondition.setElasticsearchTypeEnum(elasticsearchTypeEnum);
        delete(xsearchQueryCondition);
    }

    /**
     * @param xsearchDocument
     * @param xsearchOperationTypeEnum
     * @return
     */
    public SearchResultDocument doSearch(ElasticsearchDocument xsearchDocument,
                                          ElasticsearchOperationTypeEnum xsearchOperationTypeEnum) {
        if (xsearchDocument == null) {
            return null;
        }
        JSONObject content = (JSONObject) xsearchDocument.build();
        String url = xsearchURL + xsearchOperationTypeEnum.getUri()
                + xsearchDocument.getElasticsearchTypeEnum().getType();
        log.info("操作二向箔{}，url：{},内容：{}", xsearchOperationTypeEnum.getCode(), url, content.toJSONString());
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=utf-8");
        headers.setContentType(type);
        HttpEntity<JSONObject> formEntity = new HttpEntity<>(content, headers);

        String result = getRestTemplate().postForObject(url, formEntity, String.class);
        SearchResultDocument xsearchResultDocument = JSONObject.toJavaObject(JSONObject.parseObject(result),
                SearchResultDocument.class);
        if (xsearchResultDocument != null && xsearchDocument.getCla() != null) {
            List hitsData = xsearchResultDocument.getHitsData();
            if (hitsData != null && hitsData.size() > 0) {
                List newHitsData = new ArrayList();
                Class cla = xsearchDocument.getCla();
                try {
                    for (Object obj : hitsData) {
                        Object newObj = cla.getConstructor().newInstance();
                        BeanUtils.copyProperties(newObj, obj);
                        newHitsData.add(newObj);
                    }
                    xsearchResultDocument.setHitsData(newHitsData);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return xsearchResultDocument;
    }

    private RestTemplate getRestTemplate() {
        return SpringBeansUtil.getBean(RestTemplate.class);
    }
}
