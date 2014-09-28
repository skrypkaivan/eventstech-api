package com.eventstech.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.eventstech.annotation.DeleteIndex;
import com.eventstech.annotation.Indexed;
import com.eventstech.annotation.IndexedEntityType;
import com.eventstech.db.entity.Event;
import com.eventstech.db.entity.User;
import com.eventstech.db.repositories.EventRepository;
import com.eventstech.rest.dto.EventDto;
import com.eventstech.rest.dto.UserDto;
import com.eventstech.search.entity.EventDocument;
import com.eventstech.search.repositories.EventSearchRepository;
import com.eventstech.service.EventService;
import org.dozer.DozerBeanMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Date: 31.07.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private DozerBeanMapper dozer;


    @Indexed(repository = EventSearchRepository.class, documentClass = EventDocument.class, type = IndexedEntityType.RETURN_VALUE)
    @Override
    public EventDto save(EventDto eventDto, UserDto userDto) {
        Event event = dozer.map(eventDto, Event.class);
        User user = dozer.map(userDto, User.class);
        event.setCreator(user);
        return dozer.map(eventRepository.save(event), EventDto.class);
    }

    @Indexed(repository = EventSearchRepository.class, documentClass = EventDocument.class, type = IndexedEntityType.RETURN_VALUE)
    @Transactional
    @Override
    public EventDto update(EventDto eventDto) {
        Event event = dozer.map(eventDto, Event.class);
        Event eventOldState = eventRepository.getOne(eventDto.getId());
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
        return transform(Lists.newArrayList(eventRepository.getByTagSlug(tagSlug, new PageRequest(pageNumber - 1, pageSize))));
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

    @Transactional
    @Override
    public List<EventDto> getUncategorisedEvents(int pageNumber, int pageSize) {
        return transform(Lists.newArrayList(eventRepository.getUncategorisedEvents(new PageRequest(pageNumber - 1, pageSize))));
    }

    @DeleteIndex(repository = EventSearchRepository.class, indexParameterName = "id")
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
}
