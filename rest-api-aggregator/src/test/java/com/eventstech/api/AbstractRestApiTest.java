package com.eventstech.api;

import com.eventstech.test.JsonEntitiesTestExecutionListener;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-api-end-2-end-test-context.xml"})
@ActiveProfiles(profiles = "standalone")
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, JsonEntitiesTestExecutionListener.class})
public abstract class AbstractRestApiTest {

    public static final String APP_HOST = "http://localhost";

    @Value("${app.context.path}")
    private String contextPath;

    @Value("${app.path}")
    private String appPath;

    @Value("${tomcat.port}")
    private int appPort;

    @Autowired
    protected Gson gson;

    @Before
    public void initRestAssured() {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.baseURI = APP_HOST;
        RestAssured.port = appPort;
        RestAssured.basePath = contextPath +"/" + appPath;
    }
}
