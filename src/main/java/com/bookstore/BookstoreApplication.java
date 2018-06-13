package com.bookstore;

import com.bookstore.config.SecurityUtility;
import com.bookstore.domain.User;
import com.bookstore.domain.security.Role;
import com.bookstore.repository.RoleRepository;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {

    private UserService userService;

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public BookstoreApplication(UserService userService, UserRepository userRepository, RoleRepository roleRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        roleRepository.deleteAll();
        userRepository.deleteAll();

        roleRepository.save(new Role("ROLE_USER"));
        roleRepository.save(new Role("ROLE_ADMIN"));

        User user1 = User.builder()
                .firstName("John")
                .lastName("Adams")
                .username("nick")
                .password(SecurityUtility.passwordEncoder().encode("1111"))
                .email("john.adams@gmail.com")
                .roles(Set.of(roleRepository.findByName("ROLE_USER")))
                .build();

        userRepository.save(user1);

        userRepository.delete(userRepository.findByUsername("nick").get());
    }

//    @Override
//    public void run(String... args) throws Exception {
//        User user1 = User.builder()
//                .firstName("John")
//                .lastName("Adams")
//                .username("nick")
//                .password(SecurityUtility.passwordEncoder().encode("1111"))
//                .email("john.adams@gmail.com")
//                .userRoles(new HashSet<>())
//                .build();
//        Set<UserRole> userRoles = Set.of(new UserRole(user1, new Role(2, "ROLE_USER")));
//
//        userService.createUser(user1, userRoles);

//        User user2 = User.builder()
//                .firstName("Admin")
//                .lastName("Admin")
//                .username("admin")
//                .password(SecurityUtility.passwordEncoder().encode("1111"))
//                .email("admin@gmail.com")
//                .userRoles(new HashSet<>())
//                .build();
//        Set<UserRole> userRoles2 = Set.of(new UserRole(user2, new Role(1, "ROLE_ADMIN")));
//
//        userService.createUser(user2, userRoles2);
//    }
}
