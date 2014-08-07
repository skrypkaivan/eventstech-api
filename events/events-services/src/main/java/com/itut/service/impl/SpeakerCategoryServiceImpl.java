package com.itut.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.itut.db.entity.SpeakerCategory;
import com.itut.db.repositories.SpeakerCategoryRepository;
import com.itut.rest.dto.SpeakerCategoryDto;
import com.itut.service.SpeakerCategoryService;
import org.dozer.DozerBeanMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vanish on 8/6/14.
 */
public class SpeakerCategoryServiceImpl implements SpeakerCategoryService {

    private SpeakerCategoryRepository speakerCategoryRepository;
    private DozerBeanMapper dozer;

    @Override
    public SpeakerCategoryDto save(SpeakerCategoryDto speakerCategoryDto) {
        SpeakerCategory speakerCategory = dozer.map(speakerCategoryDto, SpeakerCategory.class);
        return dozer.map(speakerCategoryRepository.save(speakerCategory), SpeakerCategoryDto.class);
    }

    @Transactional
    @Override
    public List<SpeakerCategoryDto> findAll() {
        return Lists.transform(speakerCategoryRepository.findAll(), new Function<SpeakerCategory, SpeakerCategoryDto>() {
            @Override
            public SpeakerCategoryDto apply(SpeakerCategory input) {
                return dozer.map(input, SpeakerCategoryDto.class);
            }
        });
    }

    @Override
    public void delete(Long speakerCategoryId) {
        speakerCategoryRepository.delete(speakerCategoryId);
    }

    public void setSpeakerCategoryRepository(SpeakerCategoryRepository speakerCategoryRepository) {
        this.speakerCategoryRepository = speakerCategoryRepository;
    }

    public void setDozer(DozerBeanMapper dozer) {
        this.dozer = dozer;
    }
}
