package com.itut.service;

import com.itut.db.entity.EventCategory;
import com.itut.rest.dto.EventCategoryDto;

import java.util.List;

/**
 * Created by vanish on 7/31/14.
 */
public interface EventCategoryService {
    EventCategoryDto save(EventCategoryDto eventCategoryDto);
    List<EventCategoryDto> findAll();
    void delete(Long eventCategoryId);
}
