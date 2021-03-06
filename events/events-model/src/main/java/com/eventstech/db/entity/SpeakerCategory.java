package com.eventstech.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@Entity
@Table(name = SpeakerCategory.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(name = "speaker_category_unique_slug", columnNames = {"slug"})
})
@NamedQueries(value = {
        @NamedQuery(name = "SpeakerCategory.findAllTopLevel", query = "select ec from SpeakerCategory ec where ec.parentCategoryId is null"),
        @NamedQuery(name = "SpeakerCategory.getSubCategories", query = "select ec from SpeakerCategory ec where ec.parentCategoryId = ?1")
})
public class SpeakerCategory extends AbstractEntity {

    public static final String TABLE_NAME = "speaker_category";

    public static final int NAME_LENGTH = 100;
    public static final int SLUG_LENGTH = 30;

    @Id
    @GeneratedValue
    @Getter @Setter private Long id;

    @Column(name = "speaker_category_name", nullable = false, length = NAME_LENGTH)
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
