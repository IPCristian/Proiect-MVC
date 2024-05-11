package com.project.proiectspring.service;

import com.project.proiectspring.exception.UserNotFoundException;
import com.project.proiectspring.model.Book;
import com.project.proiectspring.model.User;
import com.project.proiectspring.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    }

    public User create(User user) { return userRepository.save(user); }

    public User update(User oldUser, User user) {
        Optional<User> existingUser = userRepository.findById(oldUser.getId());
        if (existingUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        oldUser.setEmail(user.getEmail());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setPhoneNumber(user.getPhoneNumber());
        oldUser.setPassword(user.getPassword());

        return userRepository.save(oldUser);
    }

    public List<User> get(String lastName, String firstName) {
        List<User> users = new ArrayList<>();

        if (lastName != null && firstName != null &&
            !lastName.isEmpty() && !firstName.isEmpty()) {
            users = userRepository.findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(lastName, firstName);
        } else {
            users = userRepository.findAll();
        }

        return users;
    }

    public User get(Long id) {

        if(id != null) {
            Optional<User> user = userRepository.findById(id);

            if (user.isPresent()) {
                return user.get();
            }
        }

        return null;
    }

    public User getByEmail(String email) {

        if(email != null) {
            Optional<User> user = userRepository.findByEmailIgnoreCase(email);

            if (user.isPresent()) {
                return user.get();
            }
        }

        return null;
    }

    public void delete(Long id) {

        User user = get(id);
        if (user != null)
            userRepository.delete(user);

    }

    public void grantAccess(Long userId)
    {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.grantAccess();
        }
    }
}
