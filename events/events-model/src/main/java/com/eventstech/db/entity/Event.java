package com.eventstech.db.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Set;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@Entity
@Table(name = Event.TABLE_NAME, uniqueConstraints = {
    @UniqueConstraint(name = "event_slug_unique", columnNames = {"slug"})
})
@NamedQueries(value = {
        @NamedQuery(name = "Event.getPageSpeakerSlug", query = "select e from Event e join e.speakers s where s.slug = ?1"),
        @NamedQuery(name = "Event.getPopularEvents", query = "select e from Event e where e.popular is true"),
        @NamedQuery(name = "Event.getByTagSlug", query = "select e from Event e join e.categories c where c.slug = ?1"),
        @NamedQuery(name = "Event.getBySlug", query = "select e from Event e where e.slug = ?1"),
        @NamedQuery(name = "Event.getUncategorisedEvents", query = "select e from Event e where e.categories is empty")
})
public class Event {
    public static final String TABLE_NAME = "event";

    @Id
    @GeneratedValue
    @Getter @Setter private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "event_spearks",
            joinColumns = {
                    @JoinColumn(name = "event_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "speaker_id")
            }
    )
    @Getter @Setter private Set<Speaker> speakers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "event_categories",
            joinColumns = {
                    @JoinColumn(name = "event_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "event_category_id")
            }
    )
    @Getter @Setter private Set<EventCategory> categories;

    @Column(name = "start_date", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Getter @Setter private DateTime startDate;

    @Column(name = "end_date", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Getter @Setter private DateTime endDate;

    @Column(name = "logo", nullable = false)
    @Getter @Setter private String logo;

    @Column(name = "full_name", nullable = false)
    @Getter @Setter private String fullName;

    @Column(name = "short_name", nullable = false)
    @Getter @Setter private String shortName;

    @Column(name = "city", nullable = false)
    @Getter @Setter private String city;

    @Column(name = "place", nullable = false)
    @Getter @Setter private String place;

    @Column(name = "popular")
    @Getter @Setter private boolean popular;

    @Column(name = "short_desc", nullable = false, columnDefinition = "TEXT")
    @Getter @Setter private String shortDescription;

    @Column(name = "long_desc", nullable = false, columnDefinition = "TEXT")
    @Getter @Setter private String longDescription;

    @Column(name = "slug", nullable = false)
    @Getter @Setter private String slug;

    @Column(name = "pre_moderate")
    @Getter @Setter private boolean preModerate;

    @ManyToOne(optional = false)
    @Getter @Setter private User creator;
}
