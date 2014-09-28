package com.eventstech.db.entity;

import javax.persistence.*;

/**
 * Created by vanish on 8/8/14.
 */
@Entity
@Table(name = UserRole.TABLE_NAME)
public class UserRole {

    public static final String TABLE_NAME = "user_role";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
