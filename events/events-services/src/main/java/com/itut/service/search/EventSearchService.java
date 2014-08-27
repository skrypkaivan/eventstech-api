package com.itut.service.search;

import com.itut.rest.dto.EventDto;

import java.util.List;

/**
 * Date: 27.08.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public interface EventSearchService {
    List<EventDto> findSimilar(EventDto event, int count);
}
