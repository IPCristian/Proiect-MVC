package com.project.proiectspring.bootstrap;

import com.project.proiectspring.model.User;
import com.project.proiectspring.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("nonprod")
public class DataLoader implements CommandLineRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void loadUserData() {
        User admin = new User("Ilie","Petre-Cristian","emailadmin@gmail.com","0123456789",passwordEncoder.encode("abcd"));
        userRepository.save(admin);
    }

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }
}
