package com.eventstech.api;

import com.eventstech.db.entity.User;
import com.eventstech.db.entity.UserRole;
import com.eventstech.test.JsonEntities;
import com.eventstech.test.JsonEntity;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public class LoginRestApiTest extends AbstractRestApiTest {

    private static final String ADMIN_LOGIN = "role_admin@itut.com";
    private static final String LOCKED_USER_LOGIN = "role_manager_locked@itut.com";
    private static final String DISABLED_USER_LOGIN = "role_manager_disabled@itut.com";
    private static final String WRONG_LOGIN = "wrong_login@itut.com";
    private static final String PASSWORD = "11111111";

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/user_roles.json", entityClass = UserRole.class),
            @JsonEntity(fileLocation = "testData/user.json", entityClass = User.class)
    })
    public void loginWithValidCredentialsTest() {
        given().
                formParam("login", ADMIN_LOGIN).formParam("password", PASSWORD).
        when().
                post("/login").
        then().
                statusCode(200).
                body("userName", equalTo(ADMIN_LOGIN)).
                body("authToken", notNullValue());
    }

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/user_roles.json", entityClass = UserRole.class),
            @JsonEntity(fileLocation = "testData/user.json", entityClass = User.class)
    })
    public void loginWithLockedCredentialsTest() {
        given().
                formParam("login", LOCKED_USER_LOGIN).formParam("password", PASSWORD).
        when().
                post("/login").
        then().
                statusCode(401);
    }

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/user_roles.json", entityClass = UserRole.class),
            @JsonEntity(fileLocation = "testData/user.json", entityClass = User.class)
    })
    public void loginWithDisabledCredentialsTest() {
        given().
                formParam("login", DISABLED_USER_LOGIN).formParam("password", PASSWORD).
        when().
                post("/login").
        then().
                statusCode(401);
    }

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/user_roles.json", entityClass = UserRole.class),
            @JsonEntity(fileLocation = "testData/user.json", entityClass = User.class)
    })
    public void loginWithInvalidCredentialsTest() {
        given().
                formParam("login", WRONG_LOGIN).formParam("password", PASSWORD).
        when().
                post("/login").
        then().
                statusCode(401);
    }

}
