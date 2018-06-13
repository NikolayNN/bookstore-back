package com.bookstore.service.impl;

import com.bookstore.domain.User;
import com.bookstore.domain.security.Role;
import com.bookstore.repository.RoleRepository;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public User createUser(User user, Set<Role> roles) {

        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());

        if (userOptional.isPresent()) {
            log.info("User with username {} already exist. Nothing will be done");
            return userOptional.get();
        } else {
            roles.stream().forEach(userRole -> {
                Role savedRole = roleRepository.save(userRole);
            });

            user.getRoles().addAll(roles);

            System.out.println("????? " + user);

                       User savedUser = userRepository.save(user);

            System.out.println("!!!!!! " + savedUser);

            return savedUser;
        }
    }
}
