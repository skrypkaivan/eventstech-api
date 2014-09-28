package com.eventstech.security;

import java.util.List;

/**
 * Created by vanish on 8/17/14.
 */
public class SuccessAuthResponse {
    private String userName;
    private String authToken;
    private List<String> userRoles;

    public SuccessAuthResponse(String userName, String authToken, List<String> userRoles) {
        this.userName = userName;
        this.userRoles = userRoles;
        this.authToken = authToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
