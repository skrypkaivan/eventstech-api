package com.itut.rest.dto;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by vanish on 8/6/14.
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
