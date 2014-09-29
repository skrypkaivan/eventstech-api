package com.eventstech.search.entity;

import com.eventstech.seach.entity.AbstractDocument;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@Document(indexName = AbstractDocument.INDEX_NAME, type = EventCategoryDocument.TYPE)
public class EventCategoryDocument extends AbstractDocument<Long>{

    public static final String TYPE = "event_category";

    @Field(indexAnalyzer = "itut_ngram_analyzer", type = FieldType.String)
    @Getter @Setter private String name;

    @Field(indexAnalyzer = "itut_ngram_analyzer", type = FieldType.String)
    @Getter @Setter private String slug;

    @Override
    public String getType() {
        return TYPE;
    }
}
