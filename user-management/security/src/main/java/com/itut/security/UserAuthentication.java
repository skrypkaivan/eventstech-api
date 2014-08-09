package com.itut.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * Created by vanish on 8/9/14.
 */
public class UserAuthentication extends UsernamePasswordAuthenticationToken {

    private Long id;

    public UserAuthentication(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
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
