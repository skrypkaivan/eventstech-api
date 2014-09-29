package com.eventstech.rest;

import com.eventstech.rest.dto.ValidationErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@ControllerAdvice
public class CommonRestExceptionHandler {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDto> handleValidationErrors(MethodArgumentNotValidException e) {
        BindingResult br = e.getBindingResult();
        ValidationErrorDto validationError = new ValidationErrorDto();
        for (FieldError fieldError : br.getFieldErrors()) {
            validationError.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }
}
