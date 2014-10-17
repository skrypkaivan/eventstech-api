package com.eventstech.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Date: 17.10.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthCredentials {
    @Getter @Setter
    private String userName;
    @Getter @Setter
    private String authToken;
}
