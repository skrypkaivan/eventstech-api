package com.eventstech.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.eventstech.annotation.DeleteIndex;
import com.eventstech.annotation.Indexed;
import com.eventstech.annotation.IndexedEntityType;
import com.eventstech.db.entity.SpeakerCategory;
import com.eventstech.db.repositories.SpeakerCategoryRepository;
import com.eventstech.rest.dto.SpeakerCategoryDto;
import com.eventstech.search.entity.SpeakerCategoryDocument;
import com.eventstech.search.repositories.SpeakerCategorySearchRepository;
import com.eventstech.service.SpeakerCategoryService;
import org.dozer.DozerBeanMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vanish on 8/6/14.
 */
public class SpeakerCategoryServiceImpl implements SpeakerCategoryService {

    private SpeakerCategoryRepository speakerCategoryRepository;
    private DozerBeanMapper dozer;

    @Indexed(repository = SpeakerCategorySearchRepository.class, documentClass = SpeakerCategoryDocument.class, type = IndexedEntityType.RETURN_VALUE)
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

    @DeleteIndex(repository = SpeakerCategorySearchRepository.class, indexParameterName = "speakerCategoryId")
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
