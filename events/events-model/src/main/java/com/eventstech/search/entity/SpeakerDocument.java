package com.eventstech.search.entity;

import com.eventstech.seach.entity.AbstractDocument;
import com.google.common.collect.Lists;
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
@Document(type = SpeakerDocument.TYPE, indexName = AbstractDocument.INDEX_NAME)
public class SpeakerDocument extends AbstractDocument<Long>{

    public static final String TYPE = "speaker";

    @Field(indexAnalyzer = "itut_ngram_analyzer", type = FieldType.String)
    @Getter @Setter private String name;

    @Getter @Setter private String shortDescription;
    @Getter @Setter private String longDescription;
    @Getter @Setter private boolean popular;
    @Getter @Setter private String slug;
    @Getter @Setter private String photo;

    @Field(type = FieldType.Nested)
    @Getter @Setter private List<SpeakerCategoryDocument> tags = Lists.newArrayList();

    @Override
    public String getType() {
        return TYPE;
    }
}
