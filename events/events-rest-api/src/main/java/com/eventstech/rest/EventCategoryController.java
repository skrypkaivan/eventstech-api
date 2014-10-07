package com.eventstech.rest;

import com.eventstech.rest.dto.EventCategoryDto;
import com.eventstech.rest.dto.validation.ModelExistsValidationGroup;
import com.eventstech.security.UserAuthentication;
import com.eventstech.service.EventCategoryService;
import com.eventstech.service.search.EventCategorySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@RestController
@RequestMapping(value = "/events_tag",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
public class EventCategoryController {

    @Autowired
    private EventCategoryService eventCategoryService;
    @Autowired
    private EventCategorySearchService eventCategorySearchService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<EventCategoryDto> createNewCategory(@Validated @RequestBody EventCategoryDto eventCategoryDto, @AuthenticationPrincipal UserAuthentication user) {
        return new ResponseEntity<>(eventCategoryService.save(eventCategoryDto), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EventCategoryDto> modifyEventCategory(
            @Validated(ModelExistsValidationGroup.class) @RequestBody EventCategoryDto eventCategoryDto) {
        return new ResponseEntity<>(eventCategoryService.save(eventCategoryDto), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<EventCategoryDto>> getTopLevelCategories() {
        return new ResponseEntity<>(eventCategoryService.findTopLevel(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, value = "/{categoryId}/subcategories")
    public ResponseEntity<List<EventCategoryDto>> subCategories(@PathVariable("categoryId") Long categoryId) {
        return new ResponseEntity<>(eventCategoryService.getSubCategories(categoryId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{categoryId}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity deleteCategory(@PathVariable Long categoryId) {
        eventCategoryService.delete(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value="/autocomplete", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<EventCategoryDto>> findCategory(@RequestParam("s") String searchString) {
        return new ResponseEntity<>(eventCategorySearchService.autocomplete(searchString), HttpStatus.OK);
    }
}
