package com.itut.db.repositories;

import com.itut.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vanish on 8/8/14.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findActiveByEmailAndPassword(String email, String password);
}
