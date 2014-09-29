package com.eventstech.security.service;

import com.eventstech.security.UserAuthentication;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public interface UserAuthenticationService {
    boolean checkRoles(UserAuthentication userAuthentication, String hasRoles, String ... hasNotRoles);
}
