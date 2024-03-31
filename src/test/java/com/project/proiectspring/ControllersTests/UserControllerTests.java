package com.project.proiectspring.ControllersTests;

import com.project.proiectspring.controller.UserController;
import com.project.proiectspring.dto.CreateUserDto;
import com.project.proiectspring.dto.UpdateUserDto;
import com.project.proiectspring.mapper.UserMapper;
import com.project.proiectspring.model.User;
import com.project.proiectspring.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    @Test
    void testGet() {

        when(userService.get("Abc", "Def")).thenReturn(Collections.singletonList(new User()));

        List<User> result = userController.get("Abc", "Def");
        assertEquals(1, result.size());
    }

    @Test
    void testCreate() {
        CreateUserDto createUserDto = new CreateUserDto();
        User user = new User();
        when(userMapper.createUserDtoToUser(createUserDto)).thenReturn(user);
        when(userService.create(user)).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.create(createUserDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testUpdate() {
        long id = 1;
        UpdateUserDto updateUserDto = new UpdateUserDto();
        User existingUser = new User();
        User updatedUser = new User();
        when(userService.get(id)).thenReturn(existingUser);
        when(userMapper.updateUserDtoToUser(updateUserDto)).thenReturn(updatedUser);
        when(userService.update(existingUser, updatedUser)).thenReturn(updatedUser);

        ResponseEntity<User> responseEntity = userController.update(id, updateUserDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testDelete() {
        long id = 1;
        ResponseEntity<String> responseEntity = userController.delete(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(userService, times(1)).delete(id);
    }
}
