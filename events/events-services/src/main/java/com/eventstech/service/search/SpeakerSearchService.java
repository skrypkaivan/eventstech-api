package com.eventstech.service.search;

import com.eventstech.rest.dto.SpeakerDto;

import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public interface SpeakerSearchService {
    List<SpeakerDto> autocomplete(String searchQuery);
    List<SpeakerDto> findSimilar(SpeakerDto speaker, int count);
}
