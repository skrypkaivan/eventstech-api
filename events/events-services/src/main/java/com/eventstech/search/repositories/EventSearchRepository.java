package com.eventstech.search.repositories;

import com.eventstech.search.entity.EventDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public interface EventSearchRepository extends ElasticsearchRepository<EventDocument, Long> {
}
