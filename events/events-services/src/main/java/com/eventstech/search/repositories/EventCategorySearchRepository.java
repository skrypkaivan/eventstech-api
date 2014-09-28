package com.eventstech.search.repositories;

import com.eventstech.search.entity.EventCategoryDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by vanish on 8/24/14.
 */
public interface EventCategorySearchRepository extends ElasticsearchRepository<EventCategoryDocument, Long> {
}
