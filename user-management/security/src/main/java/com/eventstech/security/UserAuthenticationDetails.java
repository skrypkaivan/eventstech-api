package com.eventstech.security;

/**
 * Created by vanish on 8/18/14.
 */
public class UserAuthenticationDetails {
    private String authToken;

    public UserAuthenticationDetails(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
