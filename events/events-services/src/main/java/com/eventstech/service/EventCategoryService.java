package com.eventstech.service;

import com.eventstech.rest.dto.EventCategoryDto;

import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public interface EventCategoryService {

    EventCategoryDto save(EventCategoryDto eventCategoryDto);

    List<EventCategoryDto> getSubCategories(Long categoryId);

    List<EventCategoryDto> findTopLevel();

    void delete(Long eventCategoryId);
}
