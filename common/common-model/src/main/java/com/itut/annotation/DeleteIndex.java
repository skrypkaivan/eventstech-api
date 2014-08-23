package com.itut.annotation;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by vanish on 8/23/14.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DeleteIndex {
    Class<? extends ElasticsearchRepository> repository();
    String indexParameterName();
    String indexParameterIdField() default "";
}
