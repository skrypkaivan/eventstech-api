package com.itut.search.repositories;

import com.itut.search.entity.SpeakerDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by vanish on 8/23/14.
 */
public interface SpeakerSearchRepository extends ElasticsearchRepository<SpeakerDocument, Long> {
}
