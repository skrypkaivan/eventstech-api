package com.itut.service.search.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.itut.rest.dto.SpeakerDto;
import com.itut.search.entity.SpeakerDocument;
import com.itut.search.repositories.SpeakerSearchRepository;
import com.itut.service.search.SpeakerSearchService;
import org.dozer.DozerBeanMapper;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.List;

/**
 * Created by vanish on 8/24/14.
 */
public class SpeakerSearchServiceImpl implements SpeakerSearchService {

    private SpeakerSearchRepository speakerSearchRepository;
    private DozerBeanMapper dozer;

    @Override
    public List<SpeakerDto> searchByNameOrSlug(String searchQuery) {
        QueryBuilder query = QueryBuilders.fuzzyLikeThisQuery("name","slug").likeText(searchQuery);
        List<SpeakerDocument> speakers = Lists.newArrayList(speakerSearchRepository.search(query));
        return Lists.transform(speakers, new Function<SpeakerDocument, SpeakerDto>() {
            @Override
            public SpeakerDto apply(SpeakerDocument input) {
                return dozer.map(input, SpeakerDto.class);
            }
        });
    }

    public void setSpeakerSearchRepository(SpeakerSearchRepository speakerSearchRepository) {
        this.speakerSearchRepository = speakerSearchRepository;
    }

    public void setDozer(DozerBeanMapper dozer) {
        this.dozer = dozer;
    }
}
