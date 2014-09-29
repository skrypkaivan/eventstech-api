package com.eventstech.db.repositories;

import com.eventstech.db.entity.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {
}
