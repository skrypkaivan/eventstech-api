package com.itut.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

/**
 * Created by vanish on 8/7/14.
 */
public class ItutObjectMapper extends ObjectMapper {

    public void init() {
        registerModule(new JodaModule());
    }
}