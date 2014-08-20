package com.itut.service.impl;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.itut.db.entity.Event;
import com.itut.db.entity.User;
import com.itut.db.repositories.EventRepository;
import com.itut.rest.dto.EventDto;
import com.itut.rest.dto.UserDto;
import com.itut.service.EventService;
import com.itut.service.ImageService;
import com.itut.service.exception.ImageUploadingException;
import org.apache.commons.io.FilenameUtils;
import org.dozer.DozerBeanMapper;
import org.joda.time.DateTime;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Date: 31.07.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private DozerBeanMapper dozer;
    private ImageService imageService;
    private String logoFolder;
    private String logoHostName;

    @Override
    public EventDto save(EventDto eventDto, UserDto userDto) {
        Event event = dozer.map(eventDto, Event.class);
        User user = dozer.map(userDto, User.class);
        event.setCreator(user);
        return dozer.map(eventRepository.save(event), EventDto.class);
    }

    @Transactional
    @Override
    public EventDto update(EventDto eventDto) {
        Event event = dozer.map(eventDto, Event.class);
        Event eventOldState = eventRepository.getOne(eventDto.get_id());
        event.setCreator(eventOldState.getCreator());
        return dozer.map(eventRepository.save(event), EventDto.class);
    }

    @Transactional
    @Override
    public List<EventDto> getPage(int pageNumber, int pageSize) {
        Sort startDateDesc =  new Sort(new Sort.Order(Sort.Direction.DESC, "startDate"));
        return transform(Lists.newArrayList(eventRepository.findAll(new PageRequest(pageNumber - 1, pageSize, startDateDesc))));
    }

    @Transactional
    @Override
    public List<EventDto> getPageBySpeakerSlug(String speakerSlug, int pageNumber, int pageSize) {
        Sort startDateDesc = new Sort(new Sort.Order(Sort.Direction.DESC, "startDate"));
        return transform(Lists.newArrayList(eventRepository.getPageSpeakerSlug(speakerSlug, new PageRequest(pageNumber - 1, pageSize, startDateDesc))));
    }

    @Transactional
    @Override
    public List<EventDto> getPopularEvent(int count) {
        return transform(eventRepository.getPopularEvents(new PageRequest(0, count)));
    }

    @Transactional
    @Override
    public List<EventDto> getByTagSlug(String tagSlug, int pageNumber, int pageSize) {
        return transform(Lists.newArrayList(eventRepository.getByTagSlug(tagSlug, new PageRequest(pageNumber, pageSize))));
    }

    @Transactional
    @Override
    public EventDto getBySlug(String slug) {
        Event event = eventRepository.getBySlug(slug);
        return event == null ? null : dozer.map(event, EventDto.class);
    }

    @Transactional
    @Override
    public UserDto getEventCreator(Long eventId) {
        Event event = eventRepository.getOne(eventId);
        return dozer.map(event.getCreator(), UserDto.class);
    }

    @Override
    public EventDto uploadLogo(MultipartFile logo, Long eventId) throws ImageUploadingException {
        Event event = eventRepository.findOne(eventId);
        String fileName = Joiner.on(".").join(Arrays.asList(new DateTime().getMillis(), FilenameUtils.getExtension(logo.getOriginalFilename())));
        String folder = Joiner.on(File.pathSeparator).join(Arrays.asList(logoFolder, eventId));
        imageService.uploadImage(logo, fileName, folder);
        event.setLogo(Joiner.on("/").join(Arrays.asList(logoHostName, eventId, fileName)));
        return dozer.map(eventRepository.save(event), EventDto.class);
    }

    @Override
    public void delete(Long id) {
        eventRepository.delete(id);
    }

    @Override
    public boolean exists(Long eventId) {
        return eventRepository.exists(eventId);
    }

    private List<EventDto> transform(List<Event> events) {
        return Lists.transform(events, new Function<Event, EventDto>() {
            @Override
            public EventDto apply(Event input) {
                return dozer.map(input, EventDto.class);
            }
        });
    }

    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void setDozer(DozerBeanMapper dozer) {
        this.dozer = dozer;
    }

    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    public void setLogoFolder(String logoFolder) {
        this.logoFolder = logoFolder;
    }

    public void setLogoHostName(String logoHostName) {
        this.logoHostName = logoHostName;
    }
}
