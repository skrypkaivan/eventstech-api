package com.eventstech.service.search;

import com.eventstech.rest.dto.EventCategoryDto;

import java.util.List;

/**
 * Created by vanish on 8/24/14.
 */
public interface EventCategorySearchService {

    List<EventCategoryDto> autocomplete(String searchQuery);
}
