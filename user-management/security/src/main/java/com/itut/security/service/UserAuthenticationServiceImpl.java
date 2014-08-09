package com.itut.security.service;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.itut.security.UserAuthentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by vanish on 8/9/14.
 */
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Override
    public boolean checkRoles(UserAuthentication userAuthentication, String hasRoles, String... hasNotRoles) {
        Collection<String> rolesNames = Collections2.transform(userAuthentication.getAuthorities(), new Function<GrantedAuthority, String>() {
            @Override
            public String apply(GrantedAuthority input) {
                return input.getAuthority();
            }
        });
        return rolesNames.contains(hasRoles) && !rolesNames.containsAll(Arrays.asList(hasNotRoles));
    }
}
