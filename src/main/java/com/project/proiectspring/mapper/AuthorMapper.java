package com.project.proiectspring.mapper;

import com.project.proiectspring.dto.CreateAuthorDto;
import com.project.proiectspring.dto.UpdateAuthorDto;
import com.project.proiectspring.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author createAuthorDtoToAuthor(CreateAuthorDto createAuthorDto)
    {
        return new Author(
                createAuthorDto.getLastName(),
                createAuthorDto.getFirstName(),
                createAuthorDto.getBiography()
        );
    }

    public Author updateAuthorDtoToAuthor(UpdateAuthorDto updateAuthorDto)
    {
        return new Author(
                updateAuthorDto.getId(),
                updateAuthorDto.getLastName(),
                updateAuthorDto.getFirstName(),
                updateAuthorDto.getBiography()
        );
    }
}
