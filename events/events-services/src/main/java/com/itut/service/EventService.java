package com.itut.service;

import com.itut.rest.dto.EventDto;

import java.util.List;

/**
 * Date: 31.07.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public interface EventService {
    EventDto save(EventDto eventDto);
    EventDto getBySlug(String slug);
    List<EventDto> getPage(int pageNumber, int pageSize);
    List<EventDto> getPageBySpeakerSlug(String speakerSlug, int pageNumber, int pageSize);
    List<EventDto> getPopularEvent(int count);
    List<EventDto> getByTagSlug(String tagSlug, int pageNumber, int pageSize);
}
