package com.itut.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.itut.db.entity.Speaker;
import com.itut.db.repositories.SpeakerRepository;
import com.itut.rest.dto.SpeakerDto;
import com.itut.service.SpeakerService;
import org.dozer.DozerBeanMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vanish on 7/31/14.
 */
public class SpeakerServiceImpl implements SpeakerService {

    private SpeakerRepository speakerRepository;
    private DozerBeanMapper dozer;

    @Override
    public SpeakerDto save(SpeakerDto speakerDto) {
        Speaker speaker = dozer.map(speakerDto, Speaker.class);
        return dozer.map(speakerRepository.save(speaker), SpeakerDto.class);
    }

    @Transactional
    @Override
    public List<SpeakerDto> getPage(int pageNumber, int pageSize) {
        return transform(Lists.newArrayList(speakerRepository.findAll(new PageRequest(pageNumber - 1, pageSize))));
    }

    @Override
    public void delete(Long speakerId) {
        speakerRepository.delete(speakerId);
    }

    @Transactional
    @Override
    public List<SpeakerDto> getPopularSpeakers(int count) {
        return transform(Lists.newArrayList(speakerRepository.getPopularSpeakers(new PageRequest(0, count))));
    }

    @Transactional
    @Override
    public SpeakerDto getSpeakerBySlug(String slug) {
        Speaker speaker = speakerRepository.getSpeakerBySlug(slug);
        return speaker == null ? null : dozer.map(speaker, SpeakerDto.class);
    }

    @Transactional
    @Override
    public List<SpeakerDto> getByTagSlug(String tagSlug, int pageNumber, int pageSize) {
        return transform(Lists.newArrayList(speakerRepository.getByTagSlug(tagSlug, new PageRequest(pageNumber - 1, pageSize))));
    }

    private List<SpeakerDto> transform(List<Speaker> speakers) {
        return Lists.transform(speakers, new Function<Speaker, SpeakerDto>() {
            @Override
            public SpeakerDto apply(Speaker input) {
                return dozer.map(input, SpeakerDto.class);
            }
        });
    }

    public void setSpeakerRepository(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    public void setDozer(DozerBeanMapper dozer) {
        this.dozer = dozer;
    }
}