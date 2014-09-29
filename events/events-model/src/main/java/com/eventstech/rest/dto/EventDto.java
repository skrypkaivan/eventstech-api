package com.eventstech.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.eventstech.rest.dto.validation.ModelExistsValidationGroup;
import com.eventstech.rest.dto.validation.ValidationMessage;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public class EventDto {

    @NotNull(groups = {ModelExistsValidationGroup.class}, message = ValidationMessage.NOT_NULL)
    @JsonProperty("_id")
    @Getter @Setter private Long id;

    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    @Getter @Setter private String name;

    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    @Getter @Setter private String shortName;

    @NotNull(message = ValidationMessage.NOT_NULL)
    @Getter @Setter private DateTime startdate;

    @NotNull(message = ValidationMessage.NOT_NULL)
    @Getter @Setter private DateTime enddate;

    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    @Getter @Setter private String logo;

    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    @Getter @Setter private String city;

    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    @Getter @Setter private String place;

    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    @Getter @Setter private String shortDescription;

    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    @Getter @Setter private String longDescription;

    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    @Getter @Setter private String slug;

    @Getter @Setter private boolean popular;
    @Getter @Setter private boolean preModerate;
    @Getter @Setter private List<EventCategoryDto> tags = Lists.newArrayList();
    @Getter @Setter private List<SpeakerDto> speakers = Lists.newArrayList();
}
