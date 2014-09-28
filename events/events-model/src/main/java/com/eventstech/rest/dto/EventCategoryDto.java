package com.eventstech.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.eventstech.db.entity.EventCategory;
import com.eventstech.rest.dto.validation.ModelExistsValidationGroup;
import com.eventstech.rest.dto.validation.ValidationMessage;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Date: 31.07.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public class EventCategoryDto {

    @NotNull(groups = {ModelExistsValidationGroup.class}, message = ValidationMessage.NOT_NULL)
    @JsonProperty("_id")
    private Long id;

    @Size(max = EventCategory.NAME_LENGTH, message = ValidationMessage.TO_LONG)
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String name;

    @Size(max = EventCategory.SLUG_LENGTH, message = ValidationMessage.TO_LONG)
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String slug;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
