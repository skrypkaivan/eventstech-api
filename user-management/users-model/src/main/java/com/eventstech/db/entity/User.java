package com.eventstech.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@Entity
@Table(name = User.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(name = "unique_user_email", columnNames = {"email"})
})
@NamedQueries(value = {
        @NamedQuery(name = "User.findActiveByEmailAndPassword", query = "select u from User u where u.enabled is true and u.nonLocked is true and u.email=?1 and u.password=?2")
})
public class User extends AbstractEntity {

    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue
    @Getter @Setter private Long id;

    @Column(name = "email", nullable = false)
    @Getter @Setter private String email;

    @Column(name = "first_name", nullable = false)
    @Getter @Setter private String firstName;

    @Column(name = "last_name", nullable = false)
    @Getter @Setter private String lastName;

    @Column(name = "non_locked")
    @Getter @Setter private boolean nonLocked;

    @Column(name = "enabled")
    @Getter @Setter private boolean enabled;

    @Column(name = "password", nullable = false)
    @Getter @Setter private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            }
    )
    @Getter @Setter private Set<UserRole> roles;

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
