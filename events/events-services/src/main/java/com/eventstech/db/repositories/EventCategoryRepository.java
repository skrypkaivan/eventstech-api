package com.eventstech.db.repositories;

import com.eventstech.db.entity.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Date: 31.07.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {
}
