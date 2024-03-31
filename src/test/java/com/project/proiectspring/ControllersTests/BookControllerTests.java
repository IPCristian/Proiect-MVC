package com.project.proiectspring.ControllersTests;

import com.project.proiectspring.controller.BookController;
import com.project.proiectspring.dto.CreateBookDto;
import com.project.proiectspring.dto.UpdateBookDto;
import com.project.proiectspring.mapper.BookMapper;
import com.project.proiectspring.model.Author;
import com.project.proiectspring.model.Book;
import com.project.proiectspring.service.BookService;
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
class BookControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookController bookController;

    @Test
    void testGet() {
        Author author = new Author();
        when(bookService.get(author)).thenReturn(Collections.singletonList(new Book()));

        List<Book> result = bookController.get(author);
        assertEquals(1, result.size());
    }

    @Test
    void testCreate() {
        CreateBookDto createBookDto = new CreateBookDto();
        Book book = new Book();
        when(bookMapper.createBookDtoToBook(createBookDto)).thenReturn(book);
        when(bookService.create(book)).thenReturn(book);

        ResponseEntity<Book> responseEntity = bookController.create(createBookDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testUpdate() {
        long id = 1;
        UpdateBookDto updateBookDto = new UpdateBookDto();
        Book existingBook = new Book();
        Book updatedBook = new Book();
        when(bookService.get(id)).thenReturn(existingBook);
        when(bookMapper.updateBookDtoToBook(updateBookDto)).thenReturn(updatedBook);
        when(bookService.update(existingBook, updatedBook)).thenReturn(updatedBook);

        ResponseEntity<Book> responseEntity = bookController.update(id, updateBookDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testBookNotFound() {
        long id = 1;
        UpdateBookDto updateBookDto = new UpdateBookDto();
        when(bookService.get(id)).thenReturn(null);

        ResponseEntity<Book> responseEntity = bookController.update(id, updateBookDto);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}

