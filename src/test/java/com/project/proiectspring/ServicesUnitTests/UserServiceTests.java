package com.project.proiectspring.ServicesUnitTests;

import com.project.proiectspring.exception.UserNotFoundException;
import com.project.proiectspring.model.User;
import com.project.proiectspring.repository.UserRepository;
import com.project.proiectspring.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreate() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.create(user);
        assertEquals(user, result);
    }

    @Test
    void testUpdate() {
        User existingUser = new User();
        User newUser = new User();
        newUser.setFirstName("abc");

        when(userRepository.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User result = userService.update(existingUser, newUser);
        assertEquals(newUser.getFirstName(), result.getFirstName());
    }

    @Test
    void testUserNotFound() {
        User nonExistingUser = new User();
        when(userRepository.findById(nonExistingUser.getId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.update(nonExistingUser, new User()));
    }

    @Test
    void testGetByLastNameAndFirstName() {
        String lastName = "abc";
        String firstName = "def";

        List<User> expectedUsers = new ArrayList<>();
        when(userRepository.findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(lastName, firstName))
                .thenReturn(expectedUsers);

        List<User> result = userService.get(lastName, firstName);
        assertEquals(expectedUsers, result);
    }

    @Test
    void testGetAll() {
        List<User> expectedUsers = Collections.singletonList(new User());
        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<User> result = userService.get(null, null);
        assertEquals(expectedUsers, result);
    }

//    @Test
//    void testGetById() {
//        long id = 1;
//        User expectedUser = new User(id, "", "", "", "", "1234567890");
//        when(userRepository.findById(id)).thenReturn(Optional.of(expectedUser));
//
//        User result = userService.get(id);
//        assertEquals(expectedUser, result);
//    }

//    @Test
//    void testDelete() {
//        long id = 1;
//        User existingUser = new User(id, "", "", "", "", "1234567890");
//        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
//        doNothing().when(userRepository).delete(existingUser);
//
//        userService.delete(id);
//        verify(userRepository, times(1)).delete(existingUser);
//    }
}
