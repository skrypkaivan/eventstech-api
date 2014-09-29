package com.eventstech.db.repositories;

import com.eventstech.db.entity.Speaker;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {

    List<Speaker> getPopularSpeakers(Pageable page);

    Speaker getSpeakerBySlug(String slug);

    List<Speaker> getByTagSlug(String tagSlug, Pageable page);

    List<Speaker> getUncategorised(Pageable page);
}
