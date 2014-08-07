package com.itut.service;

import com.itut.rest.dto.EventCategoryDto;
import com.itut.rest.dto.SpeakerCategoryDto;

import java.util.List;

/**
 * Created by vanish on 8/6/14.
 */
public interface SpeakerCategoryService {
    SpeakerCategoryDto save(SpeakerCategoryDto speakerCategoryDto);
    List<SpeakerCategoryDto> findAll();
    void delete(Long speakerCategoryId);
}
