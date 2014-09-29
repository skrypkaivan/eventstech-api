package com.eventstech.service.search.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.eventstech.rest.dto.EventCategoryDto;
import com.eventstech.rest.dto.EventDto;
import com.eventstech.search.entity.EventDocument;
import com.eventstech.search.repositories.EventSearchRepository;
import com.eventstech.service.search.EventSearchService;
import org.dozer.DozerBeanMapper;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public class EventSearchServiceImpl implements EventSearchService {

    private EventSearchRepository eventSearchRepository;
    private DozerBeanMapper dozer;

    @Override
    public List<EventDto> findSimilar(EventDto event, int count) {
        FilterBuilder idFilter = FilterBuilders.idsFilter(EventDocument.TYPE).addIds(event.getId().toString());
        FilterBuilder notCurrentEventFilter = FilterBuilders.notFilter(idFilter);
        TermsQueryBuilder query = QueryBuilders.termsQuery("tags.slug", tagsToStrings(event.getTags()));

        SearchQuery searchQuery = new NativeSearchQuery(query, notCurrentEventFilter);
        searchQuery.setPageable(new PageRequest(0, count));

        return transform(Lists.newArrayList(eventSearchRepository.search(searchQuery)));
    }

    private List<EventDto> transform(List<EventDocument> events) {
        return Lists.transform(events, new Function<EventDocument, EventDto>() {
            @Override
            public EventDto apply(EventDocument input) {
                return dozer.map(input, EventDto.class);
            }
        });
    }

    private List<String> tagsToStrings(List<EventCategoryDto> tags) {
        return Lists.transform(tags, new Function<EventCategoryDto, String>() {
            @Override
            public String apply(EventCategoryDto input) {
                return input.getSlug();
            }
        });
    }

    public void setEventSearchRepository(EventSearchRepository eventSearchRepository) {
        this.eventSearchRepository = eventSearchRepository;
    }

    public void setDozer(DozerBeanMapper dozer) {
        this.dozer = dozer;
    }
}
