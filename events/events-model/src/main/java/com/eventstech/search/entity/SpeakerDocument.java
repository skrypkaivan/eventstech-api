package com.eventstech.search.entity;

import com.google.common.collect.Lists;
import com.eventstech.seach.entity.AbstractDocument;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * Created by vanish on 8/23/14.
 */
@Document(type = SpeakerDocument.TYPE, indexName = AbstractDocument.INDEX_NAME)
public class SpeakerDocument extends AbstractDocument<Long>{

    public static final String TYPE = "speaker";
    @Field(indexAnalyzer = "itut_ngram_analyzer", type = FieldType.String)
    private String name;
    private String shortDescription;
    private String longDescription;
    private boolean popular;
    private String slug;
    private String photo;
    @Field(type = FieldType.Nested)
    private List<SpeakerCategoryDocument> tags = Lists.newArrayList();

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

    @Override
    public String getType() {
        return TYPE;
    }
}
