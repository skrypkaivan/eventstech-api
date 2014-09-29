package com.eventstech.rest;

import com.eventstech.rest.dto.SpeakerCategoryDto;
import com.eventstech.rest.dto.validation.ModelExistsValidationGroup;
import com.eventstech.service.SpeakerCategoryService;
import com.eventstech.service.search.SpeakerCategorySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@RestController
@RequestMapping(value = "/speakers_tag",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
public class SpeakerCategoryController {
    
    @Autowired
    private SpeakerCategoryService speakerCategoryService;
    @Autowired
    private SpeakerCategorySearchService speakerCategorySearchService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<SpeakerCategoryDto> createNewCategory(@Validated @RequestBody SpeakerCategoryDto speakerCategoryDto) {
        return new ResponseEntity<>(speakerCategoryService.save(speakerCategoryDto), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SpeakerCategoryDto> modifySpeakerCategory(
            @Validated(ModelExistsValidationGroup.class) @RequestBody SpeakerCategoryDto speakerCategoryDto) {
        return new ResponseEntity<>(speakerCategoryService.save(speakerCategoryDto), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<SpeakerCategoryDto>> list() {
        return new ResponseEntity<>(speakerCategoryService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{categoryId}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity deleteCategory(@PathVariable Long categoryId) {
        speakerCategoryService.delete(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/autocomplete", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<SpeakerCategoryDto>> search(@RequestParam("s") String searchQuery) {
        return new ResponseEntity<>(speakerCategorySearchService.autocomplete(searchQuery), HttpStatus.OK);
    }
}
