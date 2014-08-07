package com.itut.rest.dto;

import com.itut.rest.dto.validation.ModelExistsValidationGroup;
import com.itut.rest.dto.validation.ValidationMessage;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by vanish on 8/6/14.
 */
public class SpeakerCategoryDto {

    @NotNull(groups = {ModelExistsValidationGroup.class}, message = ValidationMessage.NOT_NULL)
    private Long _id;

    @NotNull(message = ValidationMessage.NOT_NULL)
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String name;

    @NotNull(message = ValidationMessage.NOT_NULL)
    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String slug;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
