package com.eventstech.security;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public interface UserPrincipalService {
    UserPrincipal findActiveByUsernameAndPassword(String username, String password);
}
