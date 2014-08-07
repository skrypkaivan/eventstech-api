package com.itut.rest;

import com.itut.rest.dto.EventDto;
import com.itut.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Date: 31.07.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
@RestController
@RequestMapping(value = "/events")
public class EventController {
    public static final String DEFAULT_PAGE = "1";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_POPULAR_COUNT = "4";

    @Autowired
    private EventService eventService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<EventDto> createNewSpeaker(@Validated @RequestBody EventDto eventDto) {
        return new ResponseEntity<>(eventService.save(eventDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EventDto> updateSpeaker(@Validated @RequestBody EventDto eventDto) {
        return new ResponseEntity<>(eventService.save(eventDto), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<EventDto>> list(@RequestParam(value = "page_num", defaultValue = DEFAULT_PAGE) int pageNumber,
                                                @RequestParam(value = "page_size", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        return new ResponseEntity<>(eventService.getPage(pageNumber, pageSize), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{speakerId}")
    public ResponseEntity deleteEvent(@PathVariable Long eventId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, value = "/popular")
    public ResponseEntity<List<EventDto>> getPopularEvents(@RequestParam(defaultValue = DEFAULT_POPULAR_COUNT) int count) {
        return new ResponseEntity<>(eventService.getPopularEvent(count), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, value = "/tags/{tag}")
    public ResponseEntity<List<EventDto>> getByTag(@PathVariable("tag") String tagSlug,
                                                   @RequestParam(value = "page_num", defaultValue = DEFAULT_PAGE) int pageNumber,
                                                   @RequestParam(value = "page_size", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        return new ResponseEntity<>(eventService.getByTagSlug(tagSlug, pageNumber, pageSize), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, value = "/{slug}")
    public ResponseEntity<EventDto> getBySlug(@PathVariable String slug) {
        EventDto eventDto = eventService.getBySlug(slug);
        if (eventDto != null) {
            return new ResponseEntity<>(eventDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
