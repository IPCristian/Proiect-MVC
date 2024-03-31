package com.project.proiectspring.controller;

import com.project.proiectspring.dto.CreateUserDto;
import com.project.proiectspring.dto.UpdateUserDto;
import com.project.proiectspring.mapper.UserMapper;
import com.project.proiectspring.model.User;
import com.project.proiectspring.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Operation(summary = "Get all users", description = "Retrieve all users from the database")
    @GetMapping
    public List<User> get(
            @RequestParam(required = false)
            String lastName,
            @RequestParam(required = false)
            String firstName) {

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
        User createdUser = userService.create(user);
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
            return ResponseEntity.notFound().build();
        }

        User updatedUser = userMapper.updateUserDtoToUser(updateUserDto);
        User savedUser = userService.update(existingUser,updatedUser);

        return ResponseEntity.created(URI.create("/users/" + savedUser.getId()))
                .body(savedUser);
    }

    @Operation(summary = "Delete a user", description = "Remove a user from the database")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable
            Long id) {

        userService.delete(id);

        return ResponseEntity.ok("Ok");

    }

}
