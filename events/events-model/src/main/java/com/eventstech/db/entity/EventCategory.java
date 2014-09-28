package com.eventstech.db.entity;

import javax.persistence.*;

/**
 * Date: 31.07.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
@Entity
@Table(name = EventCategory.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(name = "event_category_slug_unique", columnNames = {"slug"})
})
public class EventCategory {
    public static final String TABLE_NAME = "event_category";

    public static final int NAME_LENGTH = 100;
    public static final int SLUG_LENGTH = 30;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "event_category_name", nullable = false, length = NAME_LENGTH)
    private String name;

    @Column(name = "slug", nullable = false, length = SLUG_LENGTH)
    private String slug;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
