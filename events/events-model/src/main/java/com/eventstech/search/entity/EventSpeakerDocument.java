package com.eventstech.search.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public class EventSpeakerDocument {
    @Getter @Setter private Long id;
    @Getter @Setter private String name;
    @Getter @Setter private String slug;
}
