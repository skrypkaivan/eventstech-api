package com.eventstech.api;

import com.eventstech.db.entity.EventCategory;
import com.eventstech.db.entity.User;
import com.eventstech.db.entity.UserRole;
import com.eventstech.rest.dto.EventCategoryDto;
import com.eventstech.test.JsonEntities;
import com.eventstech.test.JsonEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public class EventCategoryRestApiTest extends AbstractRestApiTest {

    private static final String ADMIN_LOGIN = "role_admin@itut.com";
    private static final String MANAGER_LOGIN = "role_manager@itut.com";
    private static final String USER_LOGIN = "role_user@itut.com";
    private static final String PASSWORD = "11111111";

    private static final int TOP_LEVEL_WITH_SUBCATEGORIES_ID = 1;
    private static final int TOP_LEVEL_WITHOUT_SUBCATEGORIES_ID = 2;

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/event_category.json", entityClass = EventCategory.class, indexed = true)
    })
    public void getTopLevelCategories() {
        given().
        when().
                get("/events_tag").
        then().
                statusCode(200).
                body("_id", hasItems(1, 2));
    }

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/event_category.json", entityClass = EventCategory.class, indexed = true)
    })
    public void getSecondLevelCategories() {
        given().
                pathParam("category_id", TOP_LEVEL_WITH_SUBCATEGORIES_ID).
        when().
                get("/events_tag/{category_id}/subcategories").
        then().
                statusCode(200).
                body("_id", hasItems(3));
    }

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/event_category.json", entityClass = EventCategory.class, indexed = true)
    })
    public void getEmptySecondLevelCategories() {
        given().
                pathParam("category_id", TOP_LEVEL_WITHOUT_SUBCATEGORIES_ID).
        when().
                get("/events_tag/{category_id}/subcategories").
        then().
                statusCode(200).
                body(equalTo("[]"));
    }

    @Test
    public void createNewEventCategoryWithoutCredentials() throws Exception {
        EventCategoryDto ec = new EventCategoryDto();
        ec.setName("new event category");
        ec.setSlug("new_event_category");
        given().
                body(objectMapper.writeValueAsString(ec)).
                contentType(ContentType.JSON).
        when().
                put("/events_tag").
        then().
                statusCode(401);
    }

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/user_roles.json", entityClass = UserRole.class),
            @JsonEntity(fileLocation = "testData/user.json", entityClass = User.class)
    })
    public void createNewEventCategoryLoggedButNotAuthorized() throws JsonProcessingException {
        AuthCredentials credentials = login(USER_LOGIN, PASSWORD);

        EventCategoryDto ec = new EventCategoryDto();
        ec.setName("new event category");
        ec.setSlug("new_event_category");

        given().
                body(objectMapper.writeValueAsString(ec)).
                contentType(ContentType.JSON).
                header(X_AUTH_LOGIN_HEADER, credentials.getUserName()).
                header(X_AUTH_TOKEN_HEADER, credentials.getAuthToken()).
        when().
                put("/events_tag").
        then().
                statusCode(403);
    }

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/user_roles.json", entityClass = UserRole.class),
            @JsonEntity(fileLocation = "testData/user.json", entityClass = User.class)
    })
    public void createNewEventCategoryWithAdminRights() throws JsonProcessingException {
        AuthCredentials credentials = login(ADMIN_LOGIN, PASSWORD);

        EventCategoryDto ec = new EventCategoryDto();
        ec.setName("new event category");
        ec.setSlug("new_event_category");

        given().
                body(objectMapper.writeValueAsString(ec)).
                contentType(ContentType.JSON).
                header(X_AUTH_LOGIN_HEADER, credentials.getUserName()).
                header(X_AUTH_TOKEN_HEADER, credentials.getAuthToken()).
        when().
                put("/events_tag").
        then().
                body("name", equalTo(ec.getName())).
                body("slug", equalTo(ec.getSlug())).
                body("_id", notNullValue());

    }

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/user_roles.json", entityClass = UserRole.class),
            @JsonEntity(fileLocation = "testData/user.json", entityClass = User.class)
    })
    public void createNewEventCategoryWithManagerRights() throws JsonProcessingException {
        AuthCredentials credentials = login(MANAGER_LOGIN, PASSWORD);

        EventCategoryDto ec = new EventCategoryDto();
        ec.setName("new event category");
        ec.setSlug("new_event_category");

        given().
                body(objectMapper.writeValueAsString(ec)).
                contentType(ContentType.JSON).
                header(X_AUTH_LOGIN_HEADER, credentials.getUserName()).
                header(X_AUTH_TOKEN_HEADER, credentials.getAuthToken()).
        when().
                put("/events_tag").
        then().
                body("name", equalTo(ec.getName())).
                body("slug", equalTo(ec.getSlug())).
                body("_id", notNullValue());

    }

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/event_category.json", entityClass = EventCategory.class)
    })
    public void modifyEventCategoryWithoutCredentials() throws JsonProcessingException {
        EventCategoryDto ec = new EventCategoryDto();
        ec.setName("new event category");
        ec.setSlug("new_event_category");
        ec.setId(1L);
        given().
                body(objectMapper.writeValueAsString(ec)).
                contentType(ContentType.JSON).
                when().
                post("/events_tag").
                then().
                statusCode(401);
    }

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/user_roles.json", entityClass = UserRole.class),
            @JsonEntity(fileLocation = "testData/user.json", entityClass = User.class),
            @JsonEntity(fileLocation = "testData/event_category.json", entityClass = EventCategory.class)
    })
    public void modifyEventCategoryLoggedButNotAuthorized() throws JsonProcessingException {
        AuthCredentials credentials = login(USER_LOGIN, PASSWORD);

        EventCategoryDto ec = new EventCategoryDto();
        ec.setName("new event category");
        ec.setSlug("new_event_category");
        ec.setId(1L);

        given().
                body(objectMapper.writeValueAsString(ec)).
                contentType(ContentType.JSON).
                header(X_AUTH_LOGIN_HEADER, credentials.getUserName()).
                header(X_AUTH_TOKEN_HEADER, credentials.getAuthToken()).
                when().
                post("/events_tag").
                then().
                statusCode(403);
    }

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/user_roles.json", entityClass = UserRole.class),
            @JsonEntity(fileLocation = "testData/user.json", entityClass = User.class),
            @JsonEntity(fileLocation = "testData/event_category.json", entityClass = EventCategory.class)
    })
    public void modifyEventCategoryWithAdminRights() throws JsonProcessingException {
        AuthCredentials credentials = login(ADMIN_LOGIN, PASSWORD);

        EventCategoryDto ec = new EventCategoryDto();
        ec.setName("new event category");
        ec.setSlug("new_event_category");
        ec.setId(1L);

        given().
                body(objectMapper.writeValueAsString(ec)).
                contentType(ContentType.JSON).
                header(X_AUTH_LOGIN_HEADER, credentials.getUserName()).
                header(X_AUTH_TOKEN_HEADER, credentials.getAuthToken()).
                when().
                put("/events_tag").
                then().
                body("name", equalTo(ec.getName())).
                body("slug", equalTo(ec.getSlug())).
                body("_id", equalTo(ec.getId().intValue()));

    }

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/user_roles.json", entityClass = UserRole.class),
            @JsonEntity(fileLocation = "testData/user.json", entityClass = User.class),
            @JsonEntity(fileLocation = "testData/event_category.json", entityClass = EventCategory.class)
    })
    public void modifyEventCategoryWithManagerRights() throws JsonProcessingException {
        AuthCredentials credentials = login(MANAGER_LOGIN, PASSWORD);

        EventCategoryDto ec = new EventCategoryDto();
        ec.setName("new event category");
        ec.setSlug("new_event_category");
        ec.setId(1L);

        given().
                body(objectMapper.writeValueAsString(ec)).
                contentType(ContentType.JSON).
                header(X_AUTH_LOGIN_HEADER, credentials.getUserName()).
                header(X_AUTH_TOKEN_HEADER, credentials.getAuthToken()).
                when().
                put("/events_tag").
                then().
                body("name", equalTo(ec.getName())).
                body("slug", equalTo(ec.getSlug())).
                body("_id", equalTo(ec.getId().intValue()));

    }

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/event_category.json", entityClass = EventCategory.class)
    })
    public void deleteCategoryWithoutCredentials() {
        given().
                pathParam("id", 1).
        when().
                delete("/events_tag/{id}").
        then().
                statusCode(401);
    }

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/user_roles.json", entityClass = UserRole.class),
            @JsonEntity(fileLocation = "testData/user.json", entityClass = User.class),
            @JsonEntity(fileLocation = "testData/event_category.json", entityClass = EventCategory.class)
    })
    public void deleteCategoryWhenLoggedInButNotAuthorized() {
        AuthCredentials credentials = login(USER_LOGIN, PASSWORD);
        given().
                pathParam("id", 1).
                header(X_AUTH_LOGIN_HEADER, credentials.getUserName()).
                header(X_AUTH_TOKEN_HEADER, credentials.getAuthToken()).
        when().
                delete("/events_tag/{id}").
        then().
                statusCode(403);
    }

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/user_roles.json", entityClass = UserRole.class),
            @JsonEntity(fileLocation = "testData/user.json", entityClass = User.class),
            @JsonEntity(fileLocation = "testData/event_category.json", entityClass = EventCategory.class)
    })
    public void deleteEventCategory() {
        AuthCredentials credentials = login(ADMIN_LOGIN, PASSWORD);
        given().
                pathParam("id", 1).
                header(X_AUTH_LOGIN_HEADER, credentials.getUserName()).
                header(X_AUTH_TOKEN_HEADER, credentials.getAuthToken()).
        when().
                delete("/events_tag/{id}").
        then().
                statusCode(200);
    }

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/event_category.json", entityClass = EventCategory.class, indexed = true)
    })
    public void autocompleteFirstAndSecondTopCategories() {
        given().
                queryParam("s", "TOP LEVEL").
        when().
                get("/events_tag/autocomplete").
        then().
                body("_id", hasItems(1,2)).
                body("_id", hasItem(not(3)));
    }

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/event_category.json", entityClass = EventCategory.class, indexed = true)
    })
    public void autocompleteFirstTopAndSecondLevelCategories() {
        given().
                queryParam("s", "ONE").
        when().
                get("/events_tag/autocomplete").
        then().
                body("_id", hasItems(1,3)).
                body("_id", hasItem(not(2)));
    }

    @Test
    @JsonEntities(jsonEntities = {
            @JsonEntity(fileLocation = "testData/event_category.json", entityClass = EventCategory.class, indexed = true)
    })
    public void autocompleteNothingFound() {
        given().
                queryParam("s", "THREE").
        when().
                get("/events_tag/autocomplete").
        then().
                body(Matchers.equalTo("[]"));
    }
}
