package com.itut.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.itut.rest.dto.validation.ModelExistsValidationGroup;
import com.itut.rest.dto.validation.ValidationMessage;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Date: 31.07.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public class EventDto {

    @NotNull(groups = {ModelExistsValidationGroup.class}, message = ValidationMessage.NOT_NULL)
    @JsonProperty("_id")
    private Long id;
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String name;
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String shortName;
    @NotNull(message = ValidationMessage.NOT_NULL)
    private DateTime startdate;
    @NotNull(message = ValidationMessage.NOT_NULL)
    private DateTime enddate;
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String logo;
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String city;
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String place;
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String shortDescription;
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String longDescription;
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String slug;
    private boolean popular;
    private boolean preModerate;
    private List<EventCategoryDto> tags = Lists.newArrayList();
    private List<SpeakerDto> speakers = Lists.newArrayList();

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public DateTime getStartdate() {
        return startdate;
    }

    public void setStartdate(DateTime startdate) {
        this.startdate = startdate;
    }

    public DateTime getEnddate() {
        return enddate;
    }

    public void setEnddate(DateTime enddate) {
        this.enddate = enddate;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public boolean isPreModerate() {
        return preModerate;
    }

    public void setPreModerate(boolean preModerate) {
        this.preModerate = preModerate;
    }

    public List<EventCategoryDto> getTags() {
        return tags;
    }

    public void setTags(List<EventCategoryDto> tags) {
        this.tags = tags;
    }

    public List<SpeakerDto> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<SpeakerDto> speakers) {
        this.speakers = speakers;
    }
}
