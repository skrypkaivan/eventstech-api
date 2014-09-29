package com.eventstech.service.search;

import com.eventstech.rest.dto.EventCategoryDto;

import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public interface EventCategorySearchService {

    List<EventCategoryDto> autocomplete(String searchQuery);
}
