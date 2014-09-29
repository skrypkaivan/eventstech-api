package com.eventstech.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.eventstech.rest.dto.validation.ModelExistsValidationGroup;
import com.eventstech.rest.dto.validation.ValidationMessage;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public class SpeakerDto {

    @NotNull(groups = {ModelExistsValidationGroup.class}, message = ValidationMessage.NOT_NULL)
    @JsonProperty("_id")
    @Getter @Setter private Long id;

    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    @Getter @Setter private String shortDescription;

    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    @Getter @Setter private String longDescription;

    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    @Getter @Setter private String photo;

    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    @Getter @Setter private String name;

    @Getter @Setter private boolean popular;

    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    @Getter @Setter private String slug;

    @Getter @Setter private List<SpeakerCategoryDto> tags = Lists.newArrayList();

    @Getter @Setter private List<EventDto> events = Lists.newArrayList();
}
