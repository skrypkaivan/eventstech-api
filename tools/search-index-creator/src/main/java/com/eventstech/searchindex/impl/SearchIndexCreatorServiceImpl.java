package com.eventstech.searchindex.impl;

import com.eventstech.searchindex.SearchIndexCreatorService;
import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

/**
 * Date: 08.10.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public class SearchIndexCreatorServiceImpl implements SearchIndexCreatorService {

    private static final int CHUNK_SIZE = 100;

    private Map<JpaRepository<?, ?>, Class<?>> repositoryToSearchDto;
    private Map<JpaRepository<?, ?>, ElasticsearchRepository<?, ?>> repositoryToSearchRepository;

    private DozerBeanMapper dozer;

    @Override
    public void create() {
        clearIndexes();
        for (JpaRepository<?, ?> jpaRepository : repositoryToSearchDto.keySet()) {
            List<?> entities;
            int pageNumber = 0;
            do {
                entities = Lists.newArrayList(jpaRepository.findAll(new PageRequest(pageNumber++, CHUNK_SIZE)));
                if (!entities.isEmpty()) {
                    List<Object> searchDocuments = Lists.newArrayListWithExpectedSize(CHUNK_SIZE);
                    for (Object entity : entities) {
                        searchDocuments.add(dozer.map(entity, repositoryToSearchDto.get(jpaRepository)));
                    }
                    ElasticsearchRepository elasticSearchRepository = repositoryToSearchRepository.get(jpaRepository);
                    elasticSearchRepository.save(searchDocuments);
                }
            } while (!entities.isEmpty());
        }
    }

    private void clearIndexes() {
        for (ElasticsearchRepository<?,?> elasticSearchRepository: repositoryToSearchRepository.values()) {
            elasticSearchRepository.deleteAll();
        }
    }

    public Map<JpaRepository<?, ?>, Class<?>> getRepositoryToSearchDto() {
        return repositoryToSearchDto;
    }

    public void setRepositoryToSearchDto(Map<JpaRepository<?, ?>, Class<?>> repositoryToSearchDto) {
        this.repositoryToSearchDto = repositoryToSearchDto;
    }

    public Map<JpaRepository<?, ?>, ElasticsearchRepository<?, ?>> getRepositoryToSearchRepository() {
        return repositoryToSearchRepository;
    }

    public void setRepositoryToSearchRepository(Map<JpaRepository<?, ?>, ElasticsearchRepository<?, ?>> repositoryToSearchRepository) {
        this.repositoryToSearchRepository = repositoryToSearchRepository;
    }

    public void setDozer(DozerBeanMapper dozer) {
        this.dozer = dozer;
    }
}
