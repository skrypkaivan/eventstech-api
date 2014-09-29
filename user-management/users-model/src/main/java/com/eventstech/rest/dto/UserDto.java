package com.eventstech.rest.dto;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public class UserDto {
    @Getter @Setter private Long id;
    @Getter @Setter private String email;
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private boolean nonLocked;
    @Getter @Setter private boolean enabled;
    @Getter @Setter private String password;
    @Getter @Setter private List<UserRoleDto> roles = Lists.newArrayList();
}
