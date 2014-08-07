package com.itut.db.repositories;

import com.itut.db.entity.Speaker;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Date: 31.07.14
 * Author: Ivan Skrypka
 * Copyright © 2014 Statiq, Inc.
 */
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {

    List<Speaker> getPopularSpeakers(Pageable page);

    Speaker getSpeakerBySlug(String slug);

    List<Speaker> getByTagSlug(String tagSlug, Pageable page);
}
