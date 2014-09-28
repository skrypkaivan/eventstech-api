package com.eventstech.security.service;

import com.eventstech.security.UserAuthentication;

/**
 * Created by vanish on 8/9/14.
 */
public interface UserAuthenticationService {
    boolean checkRoles(UserAuthentication userAuthentication, String hasRoles, String ... hasNotRoles);
}
