package com.eventstech.swagger;

import com.mangofactory.swagger.core.DefaultSwaggerPathProvider;

/**
 * Date: 01.10.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public class EventsTechPathProvider extends DefaultSwaggerPathProvider {

    private String path;

    @Override
    public String getAppBasePath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
