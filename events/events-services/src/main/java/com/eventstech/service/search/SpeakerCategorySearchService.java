package com.eventstech.service.search;

import com.eventstech.rest.dto.SpeakerCategoryDto;

import java.util.List;

/**
 * Created by vanish on 8/24/14.
 */
public interface SpeakerCategorySearchService {
    List<SpeakerCategoryDto> autocomplete(String query);
}
