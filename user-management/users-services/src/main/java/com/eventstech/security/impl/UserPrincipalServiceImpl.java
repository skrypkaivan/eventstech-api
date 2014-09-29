package com.eventstech.security.impl;

import com.eventstech.db.entity.User;
import com.eventstech.db.repositories.UserRepository;
import com.eventstech.security.UserPrincipal;
import com.eventstech.security.UserPrincipalService;
import org.dozer.DozerBeanMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: Ivan Skrypka
 * Copyright © 2014 Eventstech.com.ua.
 */
public class UserPrincipalServiceImpl implements UserPrincipalService {

    private UserRepository userRepository;
    private DozerBeanMapper dozer;

    @Transactional
    @Override
    public UserPrincipal findActiveByUsernameAndPassword(String username, String password) {
        User user = userRepository.findActiveByEmailAndPassword(username, password);
        return user == null ? null : dozer.map(user, UserPrincipal.class);
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setDozer(DozerBeanMapper dozer) {
        this.dozer = dozer;
    }
}
