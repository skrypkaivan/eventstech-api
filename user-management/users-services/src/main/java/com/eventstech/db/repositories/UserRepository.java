package com.eventstech.db.repositories;

import com.eventstech.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findActiveByEmailAndPassword(String email, String password);
}
