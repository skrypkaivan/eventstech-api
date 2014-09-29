package com.eventstech.search.repositories;

import com.eventstech.search.entity.EventCategoryDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public interface EventCategorySearchRepository extends ElasticsearchRepository<EventCategoryDocument, Long> {
}
