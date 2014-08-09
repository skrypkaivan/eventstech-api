package com.itut.security.impl;

import com.itut.db.entity.User;
import com.itut.db.repositories.UserRepository;
import com.itut.security.UserPrincipal;
import com.itut.security.UserPrincipalService;
import org.dozer.DozerBeanMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vanish on 8/8/14.
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
