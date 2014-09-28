package com.eventstech.db.repositories;

import com.eventstech.db.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vanish on 8/8/14.
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
