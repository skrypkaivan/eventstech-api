package com.itut.service.search;

import com.itut.rest.dto.EventCategoryDto;

import java.util.List;

/**
 * Created by vanish on 8/24/14.
 */
public interface EventCategorySearchService {

    List<EventCategoryDto> autocomplete(String searchQuery);
}
