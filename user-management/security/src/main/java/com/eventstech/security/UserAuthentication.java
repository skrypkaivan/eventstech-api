package com.eventstech.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public class UserAuthentication extends UsernamePasswordAuthenticationToken {

    @Getter @Setter
    private Long id;

    public UserAuthentication(Long id,
                              String username,
                              String password,
                              UserAuthenticationDetails details,
                              Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.setDetails(details);
    }

    public String getUsername() {
        return getPrincipal().toString();
    }

    public String getPassword() {
        return getCredentials().toString();
    }
}
