package com.itut.rest;

import com.itut.rest.dto.EventCategoryDto;
import com.itut.rest.dto.validation.ModelExistsValidationGroup;
import com.itut.service.EventCategoryService;
import com.itut.service.search.EventCategorySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.itut.security.UserAuthentication;

import java.util.List;

/**
 * Created by vanish on 8/6/14.
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
    public ResponseEntity<List<EventCategoryDto>> list() {
        return new ResponseEntity<>(eventCategoryService.findAll(), HttpStatus.OK);
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
