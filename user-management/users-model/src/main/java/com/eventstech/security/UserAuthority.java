package com.eventstech.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by vanish on 8/8/14.
 */
public class UserAuthority implements GrantedAuthority {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_CONTENT = "ROLE_CONTENT";
    public static final String ROLE_MANAGER = "ROLE_MANAGER";
    public static final String ROLE_USER = "ROLE_USER";

    private String authority;

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
