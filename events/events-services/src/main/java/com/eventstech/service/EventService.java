package com.eventstech.service;

import com.eventstech.rest.dto.EventDto;
import com.eventstech.rest.dto.UserDto;

import java.util.List;

/**
 * Date: 31.07.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public interface EventService {

    EventDto save(EventDto eventDto, UserDto userDto);

    EventDto update(EventDto eventDto);

    EventDto getBySlug(String slug);

    List<EventDto> getPage(int pageNumber, int pageSize);

    List<EventDto> getPageBySpeakerSlug(String speakerSlug, int pageNumber, int pageSize);

    List<EventDto> getPopularEvent(int count);

    List<EventDto> getByTagSlug(String tagSlug, int pageNumber, int pageSize);

    List<EventDto> getUncategorisedEvents(int pageNumber, int pageSize);

    UserDto getEventCreator(Long eventId);

    boolean exists(Long eventId);

    void delete(Long id);
}
