package com.itut.db.entity;

import javax.persistence.*;

/**
 * Created by vanish on 8/6/14.
 */
@Entity
@Table(name = SpeakerCategory.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(name = "speaker_category_unique_slug", columnNames = {"slug"})
})
public class SpeakerCategory {

    public static final String TABLE_NAME = "speaker_category";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "speaker_category_name", nullable = false)
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
