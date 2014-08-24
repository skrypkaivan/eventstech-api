package com.itut.service.search;

import com.itut.rest.dto.SpeakerCategoryDto;

import java.util.List;

/**
 * Created by vanish on 8/24/14.
 */
public interface SpeakerCategorySearchService {
    List<SpeakerCategoryDto> search(String query);
}
