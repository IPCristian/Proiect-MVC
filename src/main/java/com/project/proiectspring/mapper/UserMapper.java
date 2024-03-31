package com.project.proiectspring.mapper;

import com.project.proiectspring.dto.CreateUserDto;
import com.project.proiectspring.dto.UpdateUserDto;
import com.project.proiectspring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User createUserDtoToUser(CreateUserDto createUserDto)
    {
        return new User(
                createUserDto.getLastName(),
                createUserDto.getFirstName(),
                createUserDto.getEmail(),
                createUserDto.getPhoneNumber()
        );
    }

    public User updateUserDtoToUser(UpdateUserDto updateUserDto)
    {
        return new User(
                updateUserDto.getId(),
                updateUserDto.getLastName(),
                updateUserDto.getFirstName(),
                updateUserDto.getEmail(),
                updateUserDto.getPhoneNumber()
                );
    }
}
