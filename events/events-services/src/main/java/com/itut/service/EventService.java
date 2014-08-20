package com.itut.service;

import com.itut.rest.dto.EventDto;
import com.itut.rest.dto.UserDto;
import com.itut.service.exception.ImageUploadingException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Date: 31.07.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public interface EventService {

    EventDto uploadLogo(MultipartFile logo, Long eventId) throws ImageUploadingException;

    EventDto save(EventDto eventDto, UserDto userDto);

    EventDto update(EventDto eventDto);

    EventDto getBySlug(String slug);

    List<EventDto> getPage(int pageNumber, int pageSize);

    List<EventDto> getPageBySpeakerSlug(String speakerSlug, int pageNumber, int pageSize);

    List<EventDto> getPopularEvent(int count);

    List<EventDto> getByTagSlug(String tagSlug, int pageNumber, int pageSize);

    UserDto getEventCreator(Long eventId);

    boolean exists(Long eventId);

    void delete(Long id);
}
