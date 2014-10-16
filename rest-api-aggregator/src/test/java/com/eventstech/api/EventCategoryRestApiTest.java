package com.eventstech.api;

import com.eventstech.db.entity.EventCategory;
import com.eventstech.test.JsonEntities;
import com.eventstech.test.JsonEntity;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public class EventCategoryRestApiTest extends AbstractRestApiTest {

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
}
