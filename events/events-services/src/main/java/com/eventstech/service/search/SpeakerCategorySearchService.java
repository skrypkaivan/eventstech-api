package com.eventstech.service.search;

import com.eventstech.rest.dto.SpeakerCategoryDto;

import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public interface SpeakerCategorySearchService {
    List<SpeakerCategoryDto> autocomplete(String query);
}
