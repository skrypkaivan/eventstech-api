package com.eventstech.rest.dto;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public class ValidationErrorDto {
    private List<FieldError> fieldErrors = Lists.newLinkedList();

    public void addFieldError(String path, String message) {
        fieldErrors.add(new FieldError(path, message));
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}
