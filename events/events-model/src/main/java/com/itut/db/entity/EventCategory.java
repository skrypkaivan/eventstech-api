package com.itut.db.entity;

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

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "event_category_name", nullable = false)
    private String name;

    @Column(name = "slug", nullable = false)
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
