package com.eventstech.rest;

import com.eventstech.rest.dto.EventDto;
import com.eventstech.rest.dto.SpeakerDto;
import com.eventstech.rest.dto.validation.ModelExistsValidationGroup;
import com.eventstech.service.EventService;
import com.eventstech.service.SpeakerService;
import com.eventstech.service.search.SpeakerSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vanish on 8/6/14.
 */
@RestController
@RequestMapping(value = "/speaker",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
public class SpeakerController {

    public static final String DEFAULT_PAGE = "1";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_POPULAR_COUNT = "4";
    public static final String DEFAULT_SIMILAR_COUNT = "4";

    @Autowired
    private SpeakerService speakerService;
    @Autowired
    private EventService eventService;
    @Autowired
    private SpeakerSearchService speakerSearchService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<SpeakerDto> createNewSpeaker(@Validated @RequestBody SpeakerDto speakerDto) {
        return new ResponseEntity<>(speakerService.save(speakerDto), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SpeakerDto> updateSpeaker(@Validated(ModelExistsValidationGroup.class) @RequestBody SpeakerDto speakerDto) {
        return new ResponseEntity<>(speakerService.save(speakerDto), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<SpeakerDto>> list(@RequestParam(value = "page_num", defaultValue = DEFAULT_PAGE) int pageNumber,
                                                 @RequestParam(value = "page_size", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        return new ResponseEntity<>(speakerService.getPage(pageNumber, pageSize), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{speakerId}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity deleteSpeaker(@PathVariable Long speakerId) {
        speakerService.delete(speakerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, value = "/popular")
    public ResponseEntity<List<SpeakerDto>> popularSpeakers(@RequestParam(defaultValue = DEFAULT_POPULAR_COUNT) int count){
        return new ResponseEntity<>(speakerService.getPopularSpeakers(count), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, value = "/{slug}")
    public ResponseEntity<SpeakerDto> getSpeaker(@PathVariable String slug,
                                                 @RequestParam(value = "page_num", defaultValue = DEFAULT_PAGE) int eventsPage,
                                                 @RequestParam(value = "page_size", defaultValue = DEFAULT_PAGE_SIZE) int eventsPageSize) {
        SpeakerDto speaker = speakerService.getSpeakerBySlug(slug);
        if (speaker != null) {
            List<EventDto> speakerEvents = eventService.getPageBySpeakerSlug(slug, eventsPage, eventsPageSize);
            speaker.setEvents(speakerEvents);
            return new ResponseEntity<>(speaker, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, value = "/tag/uncategorised")
    public ResponseEntity<List<SpeakerDto>> getUncategorisedSpeakers(@RequestParam(value = "page_num", defaultValue = DEFAULT_PAGE) int pageNumber,
                                                                     @RequestParam(value = "page_size", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        return new ResponseEntity<>(speakerService.getUncategorisedSpeakers(pageNumber, pageSize), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, value = "/tag/{tag}")
    public ResponseEntity<List<SpeakerDto>> getByCategory(@PathVariable String tag,
                                                          @RequestParam(value = "page_num", defaultValue = DEFAULT_PAGE) int pageNumber,
                                                          @RequestParam(value = "page_size", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        return new ResponseEntity<>(speakerService.getByTagSlug(tag, pageNumber, pageSize), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, value = "/autocomplete")
    public ResponseEntity<List<SpeakerDto>> findByNameOrSlug(@RequestParam("s") String searchQuery) {
        return new ResponseEntity<>(speakerSearchService.autocomplete(searchQuery), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, value = "/{slug}/similar")
    public ResponseEntity<List<SpeakerDto>> findSimilar(@PathVariable("slug") String slug,
                                                        @RequestParam(value = "count", defaultValue = DEFAULT_SIMILAR_COUNT) int count) {
        SpeakerDto speakerDto = speakerService.getSpeakerBySlug(slug);
        if (speakerDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(speakerSearchService.findSimilar(speakerDto, count), HttpStatus.OK);
    }
}
