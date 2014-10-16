package com.eventstech.test;

import com.eventstech.db.entity.AbstractEntity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JsonEntity {
    String fileLocation();

    Class<? extends AbstractEntity> entityClass();

    boolean indexed() default false;
}
