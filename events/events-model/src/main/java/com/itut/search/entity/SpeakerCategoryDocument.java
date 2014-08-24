package com.itut.search.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by vanish on 8/23/14.
 */
@Document(indexName = "itut", type = SpeakerCategoryDocument.DOCUMENT_NAME)
public class SpeakerCategoryDocument {

    public static final String DOCUMENT_NAME = "speaker_category";

    @Id
    private Long id;
    private String name;
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
