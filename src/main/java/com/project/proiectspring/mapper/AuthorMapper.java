package com.project.proiectspring.mapper;

import com.project.proiectspring.dto.CreateAuthorDto;
import com.project.proiectspring.dto.UpdateAuthorDto;
import com.project.proiectspring.model.Author;
import com.project.proiectspring.model.Biography;
import com.project.proiectspring.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorMapper {

    public Author createAuthorDtoToAuthor(CreateAuthorDto createAuthorDto)
    {
        Author author = new Author();
        author.setFirstName(createAuthorDto.getFirstName());
        author.setLastName(createAuthorDto.getLastName());

        return author;
    }

    public Author updateAuthorDtoToAuthor(UpdateAuthorDto updateAuthorDto)
    {
        Author author = new Author();
        author.setId(updateAuthorDto.getId());
        author.setFirstName(updateAuthorDto.getFirstName());
        author.setLastName(updateAuthorDto.getLastName());

        return author;
    }
}