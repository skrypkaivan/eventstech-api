package com.eventstech.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@Entity
@Table(name = Speaker.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(name = "speaker_unique_slug", columnNames = {"slug"})
})
@NamedQueries(value = {
        @NamedQuery(name = "Speaker.getPopularSpeakers", query = "select s from Speaker s where s.popular is true"),
        @NamedQuery(name = "Speaker.getSpeakerBySlug", query = "select s from Speaker s where s.slug = ?1"),
        @NamedQuery(name = "Speaker.getByTagSlug", query = "select s from Speaker s join s.categories c where c.slug = ?1"),
        @NamedQuery(name = "Speaker.getUncategorised", query = "select s from Speaker s where s.categories is empty")
})
public class Speaker extends AbstractEntity {
    public static final String TABLE_NAME = "speaker";

    @Id
    @GeneratedValue
    @Getter @Setter private Long id;

    @Column(name = "name", nullable = false)
    @Getter @Setter private String name;

    @Column(name = "short_desc", nullable = false, columnDefinition = "TEXT")
    @Getter @Setter private String shortDescription;

    @Column(name = "long_desc", nullable = false, columnDefinition = "TEXT")
    @Getter @Setter private String longDescription;

    @Column(name = "photo", nullable = false)
    @Getter @Setter private String photo;

    @Column(name = "slug", nullable = false)
    @Getter @Setter private String slug;

    @Column(name = "popular")
    @Getter @Setter private boolean popular;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "speaker_categories",
        joinColumns = {@JoinColumn(name = "speaker_id")},
        inverseJoinColumns = {@JoinColumn(name = "speaker_category_id")}
    )
    @Getter @Setter private Set<SpeakerCategory> categories;

    @OneToOne
    @Getter @Setter private User user;

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
