package com.eventstech.search.entity;

import com.eventstech.seach.entity.AbstractDocument;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by vanish on 8/23/14.
 */
@Document(indexName = AbstractDocument.INDEX_NAME, type = EventCategoryDocument.TYPE)
public class EventCategoryDocument extends AbstractDocument<Long>{

    public static final String TYPE = "event_category";

    @Field(indexAnalyzer = "itut_ngram_analyzer", type = FieldType.String)
    private String name;
    @Field(indexAnalyzer = "itut_ngram_analyzer", type = FieldType.String)
    private String slug;

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

    @Override
    public String getType() {
        return TYPE;
    }
}
