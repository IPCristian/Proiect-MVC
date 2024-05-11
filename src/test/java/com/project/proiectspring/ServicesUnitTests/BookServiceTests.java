package com.project.proiectspring.ServicesUnitTests;

import com.project.proiectspring.exception.BookNotFoundException;
import com.project.proiectspring.model.Author;
import com.project.proiectspring.model.Book;
import com.project.proiectspring.model.Genre;
import com.project.proiectspring.model.Publisher;
import com.project.proiectspring.repository.BookRepository;
import com.project.proiectspring.service.BookService;
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
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void testCreate() {
        Book book = new Book();
        when(bookRepository.save(book)).thenReturn(book);
        Book result = bookService.create(book);
        assertEquals(book, result);
    }

    @Test
    void testUpdate() {
        Book existingBook = new Book();
        Book newBook = new Book();
        newBook.setTitle("abc");

        when(bookRepository.findById(existingBook.getId())).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(existingBook)).thenReturn(existingBook);

        Book result = bookService.update(existingBook, newBook);
        assertEquals(newBook.getTitle(), result.getTitle());
    }

    @Test
    void testBookNotFound() {
        Book existingBook = new Book();
        Book newBook = new Book();
        newBook.setTitle("abc");

        when(bookRepository.findById(existingBook.getId())).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> bookService.update(existingBook, newBook));
    }

    @Test
    void testGetByAuthor() {
        Author author = new Author();

        List<Book> expectedBooks = new ArrayList<>();
        when(bookRepository.findByAuthorId(author.getId())).thenReturn(expectedBooks);

        List<Book> result = bookService.get(author.getId());
        assertEquals(expectedBooks, result);
    }

    @Test
    void testGetAll() {
        List<Book> expectedBooks = Collections.singletonList(new Book());
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        List<Book> result = bookService.get(null);
        assertEquals(expectedBooks, result);
    }

    @Test
    void testGetById() {
        long id = 1;
        Book expectedBook = new Book(id,"",new Author(),new Genre(),new Publisher());
        when(bookRepository.findById(id)).thenReturn(Optional.of(expectedBook));

        Book result = bookService.getByID(id);
        assertEquals(expectedBook, result);
    }
}
