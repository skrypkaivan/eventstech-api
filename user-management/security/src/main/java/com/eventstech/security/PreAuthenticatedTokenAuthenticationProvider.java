package com.eventstech.security;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@Slf4j
public class PreAuthenticatedTokenAuthenticationProvider implements AuthenticationProvider {

    private TokenService tokenService;
    private UserPrincipalService userPrincipalService;
    private long tokenTTL;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = authentication.getPrincipal().toString();
        Token token;
        try {
            token = tokenService.verifyToken(authentication.getCredentials().toString());
        } catch (Exception e) {
            log.error("Error during token verification for user {}", principal);
            throw new PreAuthenticatedCredentialsNotFoundException(String.format("Error during token verification for user %s", principal), e);
        }
        if (isTokenExpired(token)) {
            throw new PreAuthenticatedCredentialsNotFoundException("Token is expired");
        }
        String credentials = token.getExtendedInformation();
        UserPrincipal userPrincipal = userPrincipalService.findActiveByUsernameAndPassword(principal, credentials);
        if (userPrincipal == null) {
            throw new AuthenticationCredentialsNotFoundException("Wrong username or token");
        }
        return new UserAuthentication(userPrincipal.getId(),
                userPrincipal.getUsername(),
                userPrincipal.getPassword(),
                new UserAuthenticationDetails(token.getKey()),
                userPrincipal.getAuthorities());
    }

    private boolean isTokenExpired(Token token) {
        return token == null || DateTime.now().getMillis() - token.getKeyCreationTime() > tokenTTL;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(PreAuthenticatedAuthenticationToken.class);
    }

    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public void setUserPrincipalService(UserPrincipalService userPrincipalService) {
        this.userPrincipalService = userPrincipalService;
    }

    public void setTokenTTL(long tokenTTL) {
        this.tokenTTL = tokenTTL;
    }
}
