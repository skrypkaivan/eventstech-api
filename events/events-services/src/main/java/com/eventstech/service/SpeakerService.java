package com.eventstech.service;

import com.eventstech.rest.dto.SpeakerDto;

import java.util.List;

/**
 * Created by vanish on 7/31/14.
 */
public interface SpeakerService {

    SpeakerDto save(SpeakerDto speakerDto);

    void delete(Long speakerId);

    SpeakerDto getSpeakerBySlug(String slug);

    List<SpeakerDto> getPopularSpeakers(int count);

    List<SpeakerDto> getPage(int pageNumber, int pageSize);

    List<SpeakerDto> getByTagSlug(String tagSlug, int pageNumber, int pageSize);

    List<SpeakerDto> getUncategorisedSpeakers(int pageNumber, int pageSize);
}
