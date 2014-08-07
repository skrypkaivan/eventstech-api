package com.itut.rest;

import com.itut.rest.dto.EventCategoryDto;
import com.itut.rest.dto.SpeakerCategoryDto;
import com.itut.rest.dto.ValidationErrorDto;
import com.itut.rest.dto.validation.ModelExistsValidationGroup;
import com.itut.service.SpeakerCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vanish on 8/6/14.
 */
@RestController
@RequestMapping(value = "/speakers/categories",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
public class SpeakerCategoryController {
    
    @Autowired
    private SpeakerCategoryService speakerCategoryService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<SpeakerCategoryDto> createNewCategory(@Validated @RequestBody SpeakerCategoryDto speakerCategoryDto) {
        return new ResponseEntity<>(speakerCategoryService.save(speakerCategoryDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SpeakerCategoryDto> modifySpeakerCategory(
            @Validated({ModelExistsValidationGroup.class}) @RequestBody SpeakerCategoryDto speakerCategoryDto) {
        return new ResponseEntity<>(speakerCategoryService.save(speakerCategoryDto), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<SpeakerCategoryDto>> list() {
        return new ResponseEntity<>(speakerCategoryService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable Long categoryId) {
        speakerCategoryService.delete(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
