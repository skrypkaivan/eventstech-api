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
@Document(type = EventDocument.DOCUMENT_NAME, indexName = "itut")
public class EventDocument {

    public static final String DOCUMENT_NAME = "event";

    @Id
    private Long id;
    private String name;
    private String city;
    private String place;
    private String longDescription;
    private String slug;
    private boolean popular;
    private boolean preModerate;
    @Field(type = FieldType.Nested)
    private List<EventCategoryDocument> tags = Lists.newArrayList();
    @Field(type = FieldType.Nested)
    private List<EventSpeakerDocument> speakers = Lists.newArrayList();

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

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
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

    public boolean isPreModerate() {
        return preModerate;
    }

    public void setPreModerate(boolean preModerate) {
        this.preModerate = preModerate;
    }

    public List<EventCategoryDocument> getTags() {
        return tags;
    }

    public void setTags(List<EventCategoryDocument> tags) {
        this.tags = tags;
    }

    public List<EventSpeakerDocument> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<EventSpeakerDocument> speakers) {
        this.speakers = speakers;
    }
}
