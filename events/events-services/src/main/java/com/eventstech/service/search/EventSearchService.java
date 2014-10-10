package com.eventstech.service.search;

import com.eventstech.rest.dto.EventDto;

import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public interface EventSearchService {
    List<EventDto> findSimilar(EventDto event, int count);

    List<EventDto> search(String searchQuery, int count, int pageNumber);
}
