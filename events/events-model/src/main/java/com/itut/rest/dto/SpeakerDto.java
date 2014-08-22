package com.itut.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.itut.rest.dto.validation.ModelExistsValidationGroup;
import com.itut.rest.dto.validation.ValidationMessage;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by vanish on 7/31/14.
 */
public class SpeakerDto {

    @NotNull(groups = {ModelExistsValidationGroup.class}, message = ValidationMessage.NOT_NULL)
    @JsonProperty("_id")
    private Long id;
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String shortDescription;
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String longDescription;
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String photo;
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String name;
    private boolean popular;
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String slug;
    private List<SpeakerCategoryDto> tags = Lists.newArrayList();
    private List<EventDto> events = Lists.newArrayList();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public List<SpeakerCategoryDto> getTags() {
        return tags;
    }

    public void setTags(List<SpeakerCategoryDto> tags) {
        this.tags = tags;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<EventDto> getEvents() {
        return events;
    }

    public void setEvents(List<EventDto> events) {
        this.events = events;
    }
}
