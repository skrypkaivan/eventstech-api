package com.eventstech.search.repositories;

import com.eventstech.search.entity.SpeakerCategoryDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by vanish on 8/24/14.
 */
public interface SpeakerCategorySearchRepository extends ElasticsearchRepository<SpeakerCategoryDocument, Long> {
}
