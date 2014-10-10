package com.eventstech.rest;

import com.eventstech.rest.dto.EventDto;
import com.eventstech.rest.dto.UserDto;
import com.eventstech.rest.dto.validation.ModelExistsValidationGroup;
import com.eventstech.security.UserAuthentication;
import com.eventstech.security.service.UserAuthenticationService;
import com.eventstech.service.EventService;
import com.eventstech.service.search.EventSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.eventstech.security.UserAuthority.*;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@RestController
@RequestMapping(value = "/event")
public class EventController {
    public static final String DEFAULT_PAGE = "1";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_POPULAR_COUNT = "4";
    public static final String DEFAULT_SIMILAR_COUNT = "4";

    @Autowired
    private EventService eventService;
    @Autowired
    private EventSearchService eventSearchService;
    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_CONTENT')")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<EventDto> createNewEvent(@Validated @RequestBody EventDto eventDto,
                                                   @AuthenticationPrincipal UserAuthentication user) {
        if (userAuthenticationService.checkRoles(user, ROLE_CONTENT, ROLE_ADMIN, ROLE_MANAGER)) {
            eventDto.setPreModerate(true);
        }
        UserDto creator = new UserDto();
        creator.setId(user.getId());
        return new ResponseEntity<>(eventService.save(eventDto, creator), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_CONTENT')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EventDto> updateEvent(@Validated(ModelExistsValidationGroup.class) @RequestBody EventDto eventDto,
                                                @AuthenticationPrincipal UserAuthentication user) {
        if (!eventService.exists(eventDto.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!hasRightsToModify(user, eventDto.getId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(eventService.update(eventDto), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<EventDto>> list(@RequestParam(value = "page_num", defaultValue = DEFAULT_PAGE) int pageNumber,
                                               @RequestParam(value = "page_size", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        return new ResponseEntity<>(eventService.getPage(pageNumber, pageSize), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_CONTENT')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{eventId}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity deleteEvent(@PathVariable Long eventId, @AuthenticationPrincipal UserAuthentication user) {
        if (!eventService.exists(eventId)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (!hasRightsToModify(user, eventId)) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        eventService.delete(eventId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, value = "/popular")
    public ResponseEntity<List<EventDto>> getPopularEvents(@RequestParam(defaultValue = DEFAULT_POPULAR_COUNT) int count) {
        return new ResponseEntity<>(eventService.getPopularEvent(count), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, value = "/tag/uncategorised")
    public ResponseEntity<List<EventDto>> getUncategorisedEvents(@RequestParam(value = "page_num", defaultValue = DEFAULT_PAGE) int pageNumber,
                                                                 @RequestParam(value = "page_size", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        return new ResponseEntity<>(eventService.getUncategorisedEvents(pageNumber, pageSize), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, value = "/tag/{tag}")
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

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, value = "/{slug}/similar")
    public ResponseEntity<List<EventDto>> findSimilar(@PathVariable("slug") String slug,
                                                      @RequestParam(value = "count", defaultValue = DEFAULT_SIMILAR_COUNT) int count) {
        EventDto eventDto = eventService.getBySlug(slug);
        if (eventDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(eventSearchService.findSimilar(eventDto, count), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, value = "/search")
    public ResponseEntity<List<EventDto>> search(@RequestParam("s") String searchQuery,
                                                 @RequestParam(value = "page_num", defaultValue = DEFAULT_PAGE) int pageNumber,
                                                 @RequestParam(value = "page_size", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        return new ResponseEntity<>(eventSearchService.search(searchQuery, pageSize, pageNumber), HttpStatus.OK);
    }

    private boolean hasRightsToModify(UserAuthentication user, Long eventId) {
        if (userAuthenticationService.checkRoles(user, ROLE_CONTENT, ROLE_ADMIN, ROLE_MANAGER)) {
            UserDto eventCreator = eventService.getEventCreator(eventId);
            return eventCreator.getEmail().equals(user.getUsername());
        }
        return true;
    }
}
