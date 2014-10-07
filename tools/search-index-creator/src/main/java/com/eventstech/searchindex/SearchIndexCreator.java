package com.eventstech.searchindex;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Date: 07.10.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
@Slf4j
public class SearchIndexCreator {
    public static void main(String[] args) {
        log.info("***************** Starting Search Index Creator *****************");
        String configLocation = "classpath*:spring/spring-search-index-creator-context.xml";
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.getEnvironment().setActiveProfiles("standalone");

        context.load(configLocation);
        context.refresh();
        context.registerShutdownHook();

        log.info("***************** Search Index Creator Finished Work *****************");
    }
}
