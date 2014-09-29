package com.eventstech.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@AllArgsConstructor
public class UserAuthenticationDetails {
    @Getter @Setter
    private String authToken;
}
