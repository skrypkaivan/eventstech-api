package com.eventstech.security;

/**
 * Created by vanish on 8/8/14.
 */
public interface UserPrincipalService {
    UserPrincipal findActiveByUsernameAndPassword(String username, String password);
}
