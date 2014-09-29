package com.eventstech.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public class LoginPasswordAuthenticationProvider implements AuthenticationProvider {

    private UserPrincipalService userPrincipalService;
    private BasePasswordEncoder passwordEncoder;
    private TokenService tokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String hashedPassword = passwordEncoder.encodePassword(authentication.getCredentials().toString(), authentication.getName());
        UserPrincipal userPrincipal = userPrincipalService.findActiveByUsernameAndPassword(authentication.getName(), hashedPassword);

        if (userPrincipal != null) {
            Token token = tokenService.allocateToken(userPrincipal.getPassword());
            return new UserAuthentication(userPrincipal.getId(),
                    userPrincipal.getUsername(),
                    userPrincipal.getPassword(),
                    new UserAuthenticationDetails(token.getKey()),
                    userPrincipal.getAuthorities());
        }
        throw new AuthenticationCredentialsNotFoundException("Wrong username or password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }

    public void setUserPrincipalService(UserPrincipalService userPrincipalService) {
        this.userPrincipalService = userPrincipalService;
    }

    public void setPasswordEncoder(BasePasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }
}
