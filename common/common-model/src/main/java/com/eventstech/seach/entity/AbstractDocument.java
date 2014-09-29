package com.eventstech.seach.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@Setting(settingPath = "elasticsearch/settings.json")
public abstract class AbstractDocument<ID> {

    public static final String INDEX_NAME = "eventstech";

    @Id
    @Getter @Setter private ID id;

    public abstract String getType();
}
