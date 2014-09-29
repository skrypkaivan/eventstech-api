package com.eventstech.search.entity;

import com.google.common.collect.Lists;
import com.eventstech.seach.entity.AbstractDocument;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@Document(type = EventDocument.TYPE, indexName = AbstractDocument.INDEX_NAME)
public class EventDocument extends AbstractDocument<Long>{

    public static final String TYPE = "event";

    @Field(indexAnalyzer = "itut_ngram_analyzer", type = FieldType.String)
    @Getter @Setter private String name;

    @Getter @Setter private String city;
    @Getter @Setter private String place;
    @Getter @Setter private String longDescription;
    @Getter @Setter private String slug;
    @Getter @Setter private boolean popular;
    @Getter @Setter private boolean preModerate;

    @Field(type = FieldType.Nested)
    @Getter @Setter private List<EventCategoryDocument> tags = Lists.newArrayList();

    @Field(type = FieldType.Nested)
    @Getter @Setter private List<EventSpeakerDocument> speakers = Lists.newArrayList();

    @Override
    public String getType() {
        return TYPE;
    }
}
