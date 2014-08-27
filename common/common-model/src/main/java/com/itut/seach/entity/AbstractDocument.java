package com.itut.seach.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * Date: 27.08.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
@Setting(settingPath = "elasticsearch/settings.json")
public abstract class AbstractDocument<ID> {

    public static final String INDEX_NAME = "itut";

    @Id
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public abstract String getType();
}
