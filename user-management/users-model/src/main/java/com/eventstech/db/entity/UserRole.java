package com.eventstech.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
@Entity
@Table(name = UserRole.TABLE_NAME)
public class UserRole {

    public static final String TABLE_NAME = "user_role";

    @Id
    @GeneratedValue
    @Getter @Setter private Long id;

    @Column(name = "name", nullable = false)
    @Getter @Setter private String name;
}
