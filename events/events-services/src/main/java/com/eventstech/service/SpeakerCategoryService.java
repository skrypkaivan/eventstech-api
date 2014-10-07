package com.eventstech.service;

import com.eventstech.rest.dto.SpeakerCategoryDto;

import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public interface SpeakerCategoryService {

    SpeakerCategoryDto save(SpeakerCategoryDto speakerCategoryDto);

    List<SpeakerCategoryDto> getSubCategories(Long categoryId);

    List<SpeakerCategoryDto> findTopLevel();

    void delete(Long speakerCategoryId);
}
