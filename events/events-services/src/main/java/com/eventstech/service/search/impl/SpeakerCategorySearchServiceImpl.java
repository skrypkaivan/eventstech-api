package com.eventstech.service.search.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.eventstech.rest.dto.SpeakerCategoryDto;
import com.eventstech.search.entity.SpeakerCategoryDocument;
import com.eventstech.search.repositories.SpeakerCategorySearchRepository;
import com.eventstech.service.search.SpeakerCategorySearchService;
import org.dozer.DozerBeanMapper;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.List;

/**
 * Created by vanish on 8/24/14.
 */
public class SpeakerCategorySearchServiceImpl implements SpeakerCategorySearchService {

    private SpeakerCategorySearchRepository speakerCategorySearchRepository;
    private DozerBeanMapper dozer;

    @Override
    public List<SpeakerCategoryDto> autocomplete(String queryString) {
        QueryBuilder query = QueryBuilders
                .multiMatchQuery(queryString, "name", "slug")
                .type(MultiMatchQueryBuilder.Type.BEST_FIELDS);
        return Lists.transform(Lists.newArrayList(speakerCategorySearchRepository.search(query)), new Function<SpeakerCategoryDocument, SpeakerCategoryDto>() {
            @Override
            public SpeakerCategoryDto apply(SpeakerCategoryDocument input) {
                return dozer.map(input, SpeakerCategoryDto.class);
            }
        });
    }

    public void setSpeakerCategorySearchRepository(SpeakerCategorySearchRepository speakerCategorySearchRepository) {
        this.speakerCategorySearchRepository = speakerCategorySearchRepository;
    }

    public void setDozer(DozerBeanMapper dozer) {
        this.dozer = dozer;
    }
}
