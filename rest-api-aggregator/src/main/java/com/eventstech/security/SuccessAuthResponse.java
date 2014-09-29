package com.eventstech.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@AllArgsConstructor
public class SuccessAuthResponse {
    @Getter @Setter private String userName;
    @Getter @Setter private String authToken;
    @Getter @Setter private List<String> userRoles;
}
