package com.eventstech.search.repositories;

import com.eventstech.search.entity.SpeakerCategoryDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public interface SpeakerCategorySearchRepository extends ElasticsearchRepository<SpeakerCategoryDocument, Long> {
}
