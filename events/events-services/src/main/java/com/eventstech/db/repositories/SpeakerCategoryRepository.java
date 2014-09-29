package com.eventstech.db.repositories;

import com.eventstech.db.entity.SpeakerCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public interface SpeakerCategoryRepository extends JpaRepository<SpeakerCategory, Long> {
}
