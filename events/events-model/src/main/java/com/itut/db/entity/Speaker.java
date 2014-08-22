package com.itut.db.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Date: 31.07.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
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
public class Speaker {
    public static final String TABLE_NAME = "speaker";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "short_desc", nullable = false, columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "long_desc", nullable = false, columnDefinition = "TEXT")
    private String longDescription;

    @Column(name = "photo", nullable = false)
    private String photo;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "popular")
    private boolean popular;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "speaker_categories",
        joinColumns = {@JoinColumn(name = "speaker_id")},
        inverseJoinColumns = {@JoinColumn(name = "speaker_category_id")}
    )
    private Set<SpeakerCategory> categories;

    @OneToOne
    private User user;

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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Set<SpeakerCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<SpeakerCategory> categories) {
        this.categories = categories;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
