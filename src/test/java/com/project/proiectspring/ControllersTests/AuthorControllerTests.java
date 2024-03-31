package com.project.proiectspring.ControllersTests;

import com.project.proiectspring.controller.AuthorController;
import com.project.proiectspring.dto.CreateAuthorDto;
import com.project.proiectspring.dto.UpdateAuthorDto;
import com.project.proiectspring.mapper.AuthorMapper;
import com.project.proiectspring.model.Author;
import com.project.proiectspring.service.AuthorService;
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
class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorController authorController;

    @Test
    void testGet() {
        when(authorService.get("Agatha")).thenReturn(Collections.singletonList(new Author()));

        List<Author> result = authorController.get("Agatha");
        assertEquals(1, result.size());
    }

    @Test
    void testCreate() {
        CreateAuthorDto createAuthorDto = new CreateAuthorDto();
        Author author = new Author();
        when(authorMapper.createAuthorDtoToAuthor(createAuthorDto)).thenReturn(author);
        when(authorService.create(author)).thenReturn(author);

        ResponseEntity<Author> responseEntity = authorController.create(createAuthorDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testUpdate() {
        long id = 1;
        UpdateAuthorDto updateAuthorDto = new UpdateAuthorDto();
        Author existingAuthor = new Author();
        Author updatedAuthor = new Author();
        when(authorService.get(id)).thenReturn(existingAuthor);
        when(authorMapper.updateAuthorDtoToAuthor(updateAuthorDto)).thenReturn(updatedAuthor);
        when(authorService.update(existingAuthor, updatedAuthor)).thenReturn(updatedAuthor);

        ResponseEntity<Author> responseEntity = authorController.update(id, updateAuthorDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testAuthorNotFound() {
        long id = 1;
        UpdateAuthorDto updateAuthorDto = new UpdateAuthorDto();
        when(authorService.get(id)).thenReturn(null);

        ResponseEntity<Author> responseEntity = authorController.update(id, updateAuthorDto);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}