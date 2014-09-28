package com.eventstech.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vanish on 8/7/14.
 */
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails) authentication.getDetails();
        SuccessAuthResponse successAuthResponse = new SuccessAuthResponse(authentication.getName(),
                userAuthenticationDetails.getAuthToken(),
                Lists.transform(Lists.newArrayList(authentication.getAuthorities()), new AuthoritiesToString()));
        objectMapper.writeValue(response.getWriter(), successAuthResponse);
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private static class AuthoritiesToString implements Function<GrantedAuthority, String>{
        @Override
        public String apply(GrantedAuthority input) {
            return input.getAuthority();
        }
    }
}
