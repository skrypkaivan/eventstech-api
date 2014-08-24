package com.itut.search.entity;

import com.google.common.collect.Lists;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * Created by vanish on 8/23/14.
 */
@Document(type = SpeakerDocument.DOCUMENT_NAME, indexName = "itut")
public class SpeakerDocument {

    public static final String DOCUMENT_NAME = "speaker";

    @Id
    private Long id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private boolean popular;
    private String slug;
    private String photo;
    @Field(type = FieldType.Nested)
    private List<SpeakerCategoryDocument> tags = Lists.newArrayList();

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

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<SpeakerCategoryDocument> getTags() {
        return tags;
    }

    public void setTags(List<SpeakerCategoryDocument> tags) {
        this.tags = tags;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
