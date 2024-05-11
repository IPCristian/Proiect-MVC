package com.project.proiectspring.IntegrationTests;

import com.project.proiectspring.model.User;
import com.project.proiectspring.repository.UserRepository;
import com.project.proiectspring.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/schema.sql")
@Sql("/data.sql")
@Transactional
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateUser() {
        User user = new User("John", "Doe", "john@example.com", "123456", "password");
        User createdUser = userService.create(user);

        assertNotNull(createdUser.getId());
        assertEquals(user.getFirstName(), createdUser.getFirstName());
        assertEquals(user.getLastName(), createdUser.getLastName());
        assertEquals(user.getEmail(), createdUser.getEmail());
        assertEquals(user.getPassword(), createdUser.getPassword());
    }

    @Test
    void testUpdateUser() {
        User existingUser = userService.get(10L);
        assertNotNull(existingUser);

        User updatedUser = new User("Updated", "User", "updated@example.com", "123456", "updatedPassword");
        User result = userService.update(existingUser, updatedUser);

        assertEquals(updatedUser.getFirstName(), result.getFirstName());
        assertEquals(updatedUser.getLastName(), result.getLastName());
        assertEquals(updatedUser.getEmail(), result.getEmail());
        assertEquals(updatedUser.getPassword(), result.getPassword());
    }

    @Test
    void testGetAllUsers() {
        List<User> users = userService.get(null, null);
        assertEquals(10, users.size());
    }

    @Test
    void testGetUserById() {
        User user = userService.get(1L);
        assertNotNull(user);
        assertEquals("Ion", user.getFirstName());
        assertEquals("Popescu", user.getLastName());
        assertEquals("popescu_ion@example.com", user.getEmail());
    }

    @Test
    void testGetNonExistingUser() {
        User user = userService.get(15L);
        assertNull(user);
    }

    @Test
    void testDeleteUser() {
        userService.delete(1L);
        User deletedUser = userService.get(1L);
        assertNull(deletedUser);
    }

    @Test
    void testGrantAccess() {
        userService.grantAccess(1L);
        User user = userRepository.findById(1L).orElse(null);
        assertNotNull(user);
        assertTrue(user.isAdmin());
    }
}
