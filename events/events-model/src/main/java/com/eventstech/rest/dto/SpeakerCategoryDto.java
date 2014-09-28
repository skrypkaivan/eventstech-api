package com.eventstech.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.eventstech.db.entity.SpeakerCategory;
import com.eventstech.rest.dto.validation.ModelExistsValidationGroup;
import com.eventstech.rest.dto.validation.ValidationMessage;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by vanish on 8/6/14.
 */
public class SpeakerCategoryDto {

    @NotNull(groups = {ModelExistsValidationGroup.class}, message = ValidationMessage.NOT_NULL)
    @JsonProperty("_id")
    private Long id;

    @Size(max = SpeakerCategory.NAME_LENGTH, message = ValidationMessage.TO_LONG)
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String name;

    @Size(max = SpeakerCategory.SLUG_LENGTH, message = ValidationMessage.TO_LONG)
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
