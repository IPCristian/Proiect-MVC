package com.project.proiectspring.controller;

import com.project.proiectspring.dto.CreateUserDto;
import com.project.proiectspring.dto.UpdateUserDto;
import com.project.proiectspring.mapper.UserMapper;
import com.project.proiectspring.model.User;
import com.project.proiectspring.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Operation(summary = "Get all users", description = "Retrieve all users from the database")
    @GetMapping
    public List<User> get(
            @RequestParam(required = false)
            Long id,
            @RequestParam(required = false)
            String lastName,
            @RequestParam(required = false)
            String firstName,
            @RequestParam(required = false)
            String email) {

        if (id != null)
        {
            List<User> user = new ArrayList<>();
            user.add(userService.get(id));
            logger.info("Retrieving user by ID");
            return user;
        }

        if (email != null && !email.isEmpty())
        {
            List<User> user = new ArrayList<>();
            user.add(userService.getByEmail(email));
            logger.info("Retrieving user by Email");
            return user;
        }

        logger.info("Retrieving users");
        return userService.get(lastName, firstName);
    }

    @Operation(summary = "Add a new user", description = "Create a new user account with the provided information")
    @PostMapping
    public ResponseEntity<User> create(
            @RequestBody
            @Valid
            CreateUserDto createUserDto
    ) {
        User user = userMapper.createUserDtoToUser(createUserDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userService.create(user);
        logger.info("Adding a new user");
        return ResponseEntity.created(URI.create("/users/" + createdUser))
                .body(createdUser);
    }

    @Operation(summary = "Update an existing user", description = "Modify an existing user's information")
    @PutMapping("/{id}")
    public ResponseEntity<User> update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateUserDto updateUserDto
            ) {

        User existingUser = userService.get(id);

        if (existingUser == null)
        {
            logger.error("User not found...");
            return ResponseEntity.notFound().build();
        }

        User updatedUser = userMapper.updateUserDtoToUser(updateUserDto);
        User savedUser = userService.update(existingUser,updatedUser);

        logger.info("Updated user");
        return ResponseEntity.created(URI.create("/users/" + savedUser.getId()))
                .body(savedUser);
    }

    @Operation(summary = "Delete a user", description = "Remove a user from the database")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable
            Long id) {

        userService.delete(id);
        logger.info("Deleting user");
        return ResponseEntity.ok("Ok");

    }

    @PostMapping("/grant-access")
    public ResponseEntity<String> grantAccess(@RequestParam Long userId) {
        try {
            userService.grantAccess(userId);
            logger.info("User has been granted admin access");
            return ResponseEntity.ok("Access granted successfully");
        } catch (Exception e) {
            logger.error("Failed to grant access");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to grant access");
        }
    }
}
