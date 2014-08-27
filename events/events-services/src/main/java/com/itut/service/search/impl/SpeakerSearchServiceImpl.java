package com.itut.service.search.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.itut.rest.dto.SpeakerCategoryDto;
import com.itut.rest.dto.SpeakerDto;
import com.itut.search.entity.EventDocument;
import com.itut.search.entity.SpeakerDocument;
import com.itut.search.repositories.SpeakerSearchRepository;
import com.itut.service.search.SpeakerSearchService;
import org.dozer.DozerBeanMapper;
import org.elasticsearch.index.query.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * Created by vanish on 8/24/14.
 */
public class SpeakerSearchServiceImpl implements SpeakerSearchService {

    private SpeakerSearchRepository speakerSearchRepository;
    private DozerBeanMapper dozer;

    @Override
    public List<SpeakerDto> autocomplete(String searchQuery) {
        QueryBuilder query = QueryBuilders.multiMatchQuery(searchQuery, "name", "slug").type(MultiMatchQueryBuilder.Type.BEST_FIELDS);
        List<SpeakerDocument> speakers = Lists.newArrayList(speakerSearchRepository.search(query));
        return Lists.transform(speakers, new Function<SpeakerDocument, SpeakerDto>() {
            @Override
            public SpeakerDto apply(SpeakerDocument input) {
                return dozer.map(input, SpeakerDto.class);
            }
        });
    }

    @Override
    public List<SpeakerDto> findSimilar(SpeakerDto speaker, int count) {
        FilterBuilder idFilter = FilterBuilders.idsFilter(EventDocument.TYPE).addIds(speaker.getId().toString());
        FilterBuilder notCurrentEventFilter = FilterBuilders.notFilter(idFilter);
        TermsQueryBuilder query = QueryBuilders.termsQuery("tags.slug", tagsToStrings(speaker.getTags()));

        SearchQuery searchQuery = new NativeSearchQuery(query, notCurrentEventFilter);
        searchQuery.setPageable(new PageRequest(0, count));
        return transform(Lists.newArrayList(speakerSearchRepository.search(searchQuery)));
    }

    private List<SpeakerDto> transform(List<SpeakerDocument> events) {
        return Lists.transform(events, new Function<SpeakerDocument, SpeakerDto>() {
            @Override
            public SpeakerDto apply(SpeakerDocument input) {
                return dozer.map(input, SpeakerDto.class);
            }
        });
    }

    private List<String> tagsToStrings(List<SpeakerCategoryDto> tags) {
        return Lists.transform(tags, new Function<SpeakerCategoryDto, String>() {
            @Override
            public String apply(SpeakerCategoryDto input) {
                return input.getSlug();
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
