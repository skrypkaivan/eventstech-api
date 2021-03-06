package com.eventstech.service.search.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.eventstech.rest.dto.EventCategoryDto;
import com.eventstech.search.entity.EventCategoryDocument;
import com.eventstech.search.repositories.EventCategorySearchRepository;
import com.eventstech.service.search.EventCategorySearchService;
import org.dozer.DozerBeanMapper;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public class EventCategorySearchServiceImpl implements EventCategorySearchService {

    private EventCategorySearchRepository eventCategorySearchRepository;
    private DozerBeanMapper dozer;

    @Override
    public List<EventCategoryDto> autocomplete(String searchQuery) {
        QueryBuilder query = QueryBuilders
                .multiMatchQuery(searchQuery, "name", "slug")
                .type(MultiMatchQueryBuilder.Type.BEST_FIELDS);
        return Lists.transform(Lists.newArrayList(eventCategorySearchRepository.search(query)), new Function<EventCategoryDocument, EventCategoryDto>() {
            @Override
            public EventCategoryDto apply(EventCategoryDocument input) {
                return dozer.map(input, EventCategoryDto.class);
            }
        });
    }

    public void setEventCategorySearchRepository(EventCategorySearchRepository eventCategorySearchRepository) {
        this.eventCategorySearchRepository = eventCategorySearchRepository;
    }

    public void setDozer(DozerBeanMapper dozer) {
        this.dozer = dozer;
    }
}
