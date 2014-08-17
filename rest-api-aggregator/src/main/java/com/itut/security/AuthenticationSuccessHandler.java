package com.itut.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

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

        SuccessAuthResponse successAuthResponse = new SuccessAuthResponse(authentication.getName(),
                Lists.transform(Lists.newArrayList(authentication.getAuthorities()), new AuthoritiesToString()));
        clearAuthenticationAttributes(request);
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
