package com.itut.search.entity;

import com.itut.seach.entity.AbstractDocument;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by vanish on 8/23/14.
 */
@Document(indexName = AbstractDocument.INDEX_NAME, type = SpeakerCategoryDocument.TYPE)
public class SpeakerCategoryDocument extends AbstractDocument<Long> {

    public static final String TYPE = "speaker_category";

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
