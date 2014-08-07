package com.itut.db.repositories;

import com.itut.db.entity.SpeakerCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vanish on 8/6/14.
 */
public interface SpeakerCategoryRepository extends JpaRepository<SpeakerCategory, Long> {
}
