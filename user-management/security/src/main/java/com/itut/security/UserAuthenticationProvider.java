package com.itut.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by vanish on 8/8/14.
 */
public class UserAuthenticationProvider implements AuthenticationProvider {

    private UserPrincipalService userPrincipalService;
    private BasePasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String hashedPassword = passwordEncoder.encodePassword(authentication.getCredentials().toString(), authentication.getName());
        UserPrincipal userPrincipal = userPrincipalService.findActiveByUsernameAndPassword(authentication.getName(), hashedPassword);
        if (userPrincipal != null) {
            return new UserAuthentication(userPrincipal.getId(), userPrincipal.getUsername(),
                    userPrincipal.getPassword(), userPrincipal.getAuthorities());
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
}
