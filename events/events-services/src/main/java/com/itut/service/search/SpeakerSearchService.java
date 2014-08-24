package com.itut.service.search;

import com.itut.rest.dto.SpeakerDto;

import java.util.List;

/**
 * Created by vanish on 8/24/14.
 */
public interface SpeakerSearchService {
    List<SpeakerDto> searchByNameOrSlug(String searchQuery);
}
