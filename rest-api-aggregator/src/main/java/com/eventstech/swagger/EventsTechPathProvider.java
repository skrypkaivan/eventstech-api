package com.eventstech.swagger;

import com.mangofactory.swagger.core.DefaultSwaggerPathProvider;

/**
 * Date: 01.10.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public class EventsTechPathProvider extends DefaultSwaggerPathProvider {
    @Override
    public String getAppBasePath() {
        return "/api";
    }
}
