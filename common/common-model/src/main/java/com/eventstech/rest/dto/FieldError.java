package com.eventstech.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@AllArgsConstructor
public class FieldError {
    @Getter @Setter private String field;
    @Getter @Setter private String message;
}
