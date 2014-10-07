package com.eventstech.db.repositories;

import com.eventstech.db.entity.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {

    List<EventCategory> findAllTopLevel();

    List<EventCategory> getSubCategories(Long categoryId);
}
