package com.eventstech.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

/**
 * Created by vanish on 8/7/14.
 */
public class EventsTechObjectMapper extends ObjectMapper {

    public void init() {
        registerModule(new JodaModule());
    }
}