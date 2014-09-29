package com.eventstech.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public class EventsTechObjectMapper extends ObjectMapper {

    public void init() {
        registerModule(new JodaModule());
    }
}