package com.itut.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.itut.db.entity.EventCategory;
import com.itut.db.repositories.EventCategoryRepository;
import com.itut.rest.dto.EventCategoryDto;
import com.itut.service.EventCategoryService;
import org.dozer.DozerBeanMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vanish on 7/31/14.
 */
public class EventCategoryServiceImpl implements EventCategoryService {

    private EventCategoryRepository eventCategoryRepository;
    private DozerBeanMapper dozer;

    @Override
    public EventCategoryDto save(EventCategoryDto eventCategoryDto) {
        EventCategory eventCategory = dozer.map(eventCategoryDto, EventCategory.class);
        eventCategoryRepository.save(eventCategory);
        return dozer.map(eventCategory, EventCategoryDto.class);
    }

    @Transactional
    @Override
    public List<EventCategoryDto> findAll() {
        return Lists.transform(eventCategoryRepository.findAll(), new Function<EventCategory, EventCategoryDto>() {
            @Override
            public EventCategoryDto apply(EventCategory input) {
                return dozer.map(input, EventCategoryDto.class);
            }
        });
    }

    @Override
    public void delete(Long eventCategoryId) {
        eventCategoryRepository.delete(eventCategoryId);
    }

    public void setEventCategoryRepository(EventCategoryRepository eventCategoryRepository) {
        this.eventCategoryRepository = eventCategoryRepository;
    }

    public void setDozer(DozerBeanMapper dozer) {
        this.dozer = dozer;
    }
}