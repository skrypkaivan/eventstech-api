package com.eventstech.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by vanish on 8/9/14.
 */
public class UserAuthentication extends UsernamePasswordAuthenticationToken {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}