package ru.kata.spring.boot_security.demo.createTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;


import javax.annotation.PostConstruct;
import javax.transaction.Transactional;


@Component
public class Init {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Init(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void initializeDB() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

        User user = new User();
        user.setAge(10L);
        user.setEmail("Admin@mail.ru");
        user.setFirstName("Andrey");
        user.setLastName("Rum");
        user.setPassword(passwordEncoder.encode("100"));
        user.getRoles().add(roleRepository.findRoleByRole("ROLE_ADMIN"));
        userRepository.save(user);

        User user2 = new User();
        user2.setAge(20L);
        user2.setEmail("User@mail.ru");
        user2.setFirstName("Fedor");
        user2.setLastName("Fedorov");
        user2.setPassword(passwordEncoder.encode("100"));
        user2.getRoles().add(roleRepository.findRoleByRole("ROLE_USER"));
        userRepository.save(user2);
    }
}
