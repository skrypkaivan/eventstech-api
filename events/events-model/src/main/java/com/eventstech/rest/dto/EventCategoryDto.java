package com.eventstech.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.eventstech.db.entity.EventCategory;
import com.eventstech.rest.dto.validation.ModelExistsValidationGroup;
import com.eventstech.rest.dto.validation.ValidationMessage;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public class EventCategoryDto {

    @NotNull(groups = {ModelExistsValidationGroup.class}, message = ValidationMessage.NOT_NULL)
    @JsonProperty("_id")
    @Getter @Setter private Long id;

    @Size(max = EventCategory.NAME_LENGTH, message = ValidationMessage.TO_LONG)
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    @Getter @Setter private String name;

    @Size(max = EventCategory.SLUG_LENGTH, message = ValidationMessage.TO_LONG)
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    @Getter @Setter private String slug;

    @Getter @Setter
    @JsonProperty("parent_id")
    private Long parentCategoryId;
}
