package com.itut.db.entity;


import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Set;

/**
 * Date: 31.07.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
@Entity
@Table(name = Event.TABLE_NAME, uniqueConstraints = {
    @UniqueConstraint(name = "event_slug_unique", columnNames = {"slug"})
})
@NamedQueries(value = {
        @NamedQuery(name = "Event.getPageSpeakerSlug", query = "select e from Event e join e.speakers s where s.slug = ?1"),
        @NamedQuery(name = "Event.getPopularEvents", query = "select e from Event e where e.popular is true"),
        @NamedQuery(name = "Event.getByTagSlug", query = "select e from Event e join e.categories c where c.slug = ?1"),
        @NamedQuery(name = "Event.getBySlug", query = "select e from Event e where e.slug = ?1")
})
public class Event {
    public static final String TABLE_NAME = "event";

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "event_spearks",
            joinColumns = {
                    @JoinColumn(name = "event_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "speaker_id")
            }
    )
    private Set<Speaker> speakers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "event_categories",
            joinColumns = {
                    @JoinColumn(name = "event_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "event_category_id")
            }
    )
    private Set<EventCategory> categories;

    @Column(name = "start_date", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime startDate;

    @Column(name = "end_date", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime endDate;

    @Column(name = "logo", nullable = false)
    private String logo;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "short_name", nullable = false)
    private String shortName;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "place", nullable = false)
    private String place;

    @Column(name = "popular")
    private boolean popular;

    @Column(name = "short_desc", nullable = false)
    private String shortDescription;

    @Column(name = "long_desc", nullable = false)
    private String longDescription;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "pre_moderate")
    private boolean preModerate;

    @ManyToOne(optional = false)
    private User creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Set<Speaker> speakers) {
        this.speakers = speakers;
    }

    public Set<EventCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<EventCategory> categories) {
        this.categories = categories;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
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

    public boolean isPreModerate() {
        return preModerate;
    }

    public void setPreModerate(boolean preModerate) {
        this.preModerate = preModerate;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
