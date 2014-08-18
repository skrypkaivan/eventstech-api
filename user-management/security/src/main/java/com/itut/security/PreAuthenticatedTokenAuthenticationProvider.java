package com.itut.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;

/**
 * Created by vanish on 8/18/14.
 */
public class PreAuthenticatedTokenAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(PreAuthenticatedTokenAuthenticationProvider.class);

    private TokenService tokenService;
    private UserPrincipalService userPrincipalService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = authentication.getPrincipal().toString();
        try {
            Token token = tokenService.verifyToken(authentication.getCredentials().toString());
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
        } catch (Exception e) {
            LOG.error("Error during token verification for user {}", principal);
            throw new PreAuthenticatedCredentialsNotFoundException(String.format("Error during token verification for user %s", principal), e);
        }
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
}
