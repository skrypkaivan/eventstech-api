package com.itut.rest.dto;


import com.itut.rest.dto.validation.ModelExistsValidationGroup;
import com.itut.rest.dto.validation.ValidationMessage;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Date: 31.07.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public class EventCategoryDto {

    @NotNull(groups = {ModelExistsValidationGroup.class}, message = ValidationMessage.NOT_NULL)
    private Long _id;

    @NotEmpty(message = ValidationMessage.NOT_EMPTY)
    private String name;

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
