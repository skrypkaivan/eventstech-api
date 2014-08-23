package com.itut.search.entity;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by vanish on 8/23/14.
 */
public class SpeakerCategoryDocument {
    private String name;
    private String slug;
    private Long id;

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
