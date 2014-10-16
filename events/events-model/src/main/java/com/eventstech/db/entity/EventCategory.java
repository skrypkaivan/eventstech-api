package com.eventstech.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@Entity
@Table(name = EventCategory.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(name = "event_category_slug_unique", columnNames = {"slug"})
})
@NamedQueries(value = {
    @NamedQuery(name = "EventCategory.findAllTopLevel", query = "select ec from EventCategory ec where ec.parentCategoryId is null"),
    @NamedQuery(name = "EventCategory.getSubCategories", query = "select ec from EventCategory ec where ec.parentCategoryId = ?1")
})
public class EventCategory extends AbstractEntity {
    public static final String TABLE_NAME = "event_category";

    public static final int NAME_LENGTH = 100;
    public static final int SLUG_LENGTH = 30;

    @Id
    @GeneratedValue
    @Getter @Setter private Long id;

    @Column(name = "event_category_name", nullable = false, length = NAME_LENGTH)
    @Getter @Setter private String name;

    @Column(name = "slug", nullable = false, length = SLUG_LENGTH)
    @Getter @Setter private String slug;

    @Column(name = "parent_id")
    @Getter @Setter
    private Long parentCategoryId;

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
