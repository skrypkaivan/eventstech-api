package com.eventstech.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Date: 15.10.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-api-embedded-test-context.xml"})
public abstract class AbstractRestApiTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeClass
    public static void initRestAssured() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9090;
        RestAssured.basePath = "/eventstech/app";
    }
}
