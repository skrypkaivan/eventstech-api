package com.eventstech.db.repositories;

import com.eventstech.db.entity.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public interface EventRepository extends JpaRepository<Event, Long> {
    Event getBySlug(String slug);
    List<Event> getPageSpeakerSlug(String slug, Pageable page);
    List<Event> getPopularEvents(Pageable page);
    List<Event> getByTagSlug(String tagSlug, Pageable page);
    List<Event> getUncategorisedEvents(Pageable page);
}
