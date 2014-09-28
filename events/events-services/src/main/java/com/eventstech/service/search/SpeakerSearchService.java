package com.eventstech.service.search;

import com.eventstech.rest.dto.SpeakerDto;

import java.util.List;

/**
 * Created by vanish on 8/24/14.
 */
public interface SpeakerSearchService {
    List<SpeakerDto> autocomplete(String searchQuery);
    List<SpeakerDto> findSimilar(SpeakerDto speaker, int count);
}
