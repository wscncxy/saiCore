package com.sai.core.utils.es;

import lombok.Data;

@Data
public class ElasticsearchBucket {
    private int doc_count;
    private String key;
}
