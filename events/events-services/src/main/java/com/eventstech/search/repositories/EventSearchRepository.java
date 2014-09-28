package com.eventstech.search.repositories;

import com.eventstech.search.entity.EventDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by vanish on 8/23/14.
 */
public interface EventSearchRepository extends ElasticsearchRepository<EventDocument, Long> {
}
