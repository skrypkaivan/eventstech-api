package com.itut.search.repositories;

import com.itut.search.entity.EventCategoryDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by vanish on 8/24/14.
 */
public interface EventCategorySearchRepository extends ElasticsearchRepository<EventCategoryDocument, Long> {
}
