package com.itut.search.repositories;

import com.itut.search.entity.EventDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by vanish on 8/23/14.
 */
public interface EventSearchRepository extends ElasticsearchRepository<EventDocument, Long> {
}
