package com.sai.core.utils.es;

import lombok.Data;

import java.util.List;

@Data
public class ElasticsearchAggregations {
    private int               doc_count_error_upper_bound;
    private int               sum_other_doc_count;
    List<ElasticsearchBucket> buckets;
}
