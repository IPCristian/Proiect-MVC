package com.project.proiectspring.ServicesTests;

import com.project.proiectspring.exception.AuthorNotFoundException;
import com.project.proiectspring.model.Author;
import com.project.proiectspring.repository.AuthorRepository;
import com.project.proiectspring.service.AuthorService;
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
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void testCreate() {
        Author author = new Author();
        when(authorRepository.save(author)).thenReturn(author);

        Author result = authorService.create(author);
        assertEquals(author, result);
    }

    @Test
    void testUpdate() {
        Author existingAuthor = new Author();
        Author newAuthor = new Author();
        newAuthor.setFirstName("Abc");

        when(authorRepository.findById(existingAuthor.getId())).thenReturn(Optional.of(existingAuthor));
        when(authorRepository.save(existingAuthor)).thenReturn(existingAuthor);

        Author result = authorService.update(existingAuthor, newAuthor);
        assertEquals(newAuthor.getFirstName(), result.getFirstName());
    }

    @Test
    void testAuthorNotFound() {
        Author existingAuthor = new Author();
        Author newAuthor = new Author();
        newAuthor.setFirstName("abc");

        when(authorRepository.findById(existingAuthor.getId())).thenReturn(Optional.empty());
        assertThrows(AuthorNotFoundException.class, () -> authorService.update(existingAuthor, newAuthor));
    }

    @Test
    void testGetByLastName() {
        String lastName = "Abc";

        List<Author> expectedAuthors = new ArrayList<>();
        when(authorRepository.findByLastName(lastName)).thenReturn(expectedAuthors);

        List<Author> result = authorService.get(lastName);
        assertEquals(expectedAuthors, result);
    }

    @Test
    void testGetAll() {
        List<Author> expectedAuthors = Collections.singletonList(new Author());
        when(authorRepository.findAll()).thenReturn(expectedAuthors);

        List<Author> result = authorService.get((String) null);
        assertEquals(expectedAuthors, result);
    }

    @Test
    void testGetById() {
        long id = 1;
        Author expectedAuthor = new Author(id, "", "", "");
        when(authorRepository.findById(id)).thenReturn(Optional.of(expectedAuthor));

        Author result = authorService.get(id);
        assertEquals(expectedAuthor, result);
    }
}