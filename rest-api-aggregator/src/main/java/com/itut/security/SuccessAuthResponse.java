package com.itut.security;

import java.util.List;

/**
 * Created by vanish on 8/17/14.
 */
public class SuccessAuthResponse {
    private String userName;
    private List<String> userRoles;

    public SuccessAuthResponse(String userName, List<String> userRoles) {
        this.userName = userName;
        this.userRoles = userRoles;
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
}
