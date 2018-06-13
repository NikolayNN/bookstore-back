package com.bookstore.service;

import com.bookstore.domain.User;
import com.bookstore.domain.security.Role;

import java.util.Set;

public interface UserService {

    User createUser(User user, Set<Role> userRoles);
}
