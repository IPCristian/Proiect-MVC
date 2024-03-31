package com.project.proiectspring.controller;

import com.project.proiectspring.dto.CreateBookDto;
import com.project.proiectspring.dto.UpdateBookDto;
import com.project.proiectspring.mapper.BookMapper;
import com.project.proiectspring.model.Author;
import com.project.proiectspring.model.Book;
import com.project.proiectspring.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @Operation(summary = "Get all books", description = "Retrieve all book data from the database")
    @GetMapping
    public List<Book> get(
            @RequestParam(required = false)
            Author author) {

        return bookService.get(author);
    }

    @Operation(summary = "Add a new book", description = "Create a new book with the provided details")
    @PostMapping
    public ResponseEntity<Book> create(
            @RequestBody
            @Valid
            CreateBookDto createBookDto
    ) {
        Book book  = bookMapper.createBookDtoToBook(createBookDto);
        Book createdBook = bookService.create(book);
        return ResponseEntity.created(URI.create("/books/" + createdBook.getId()))
                .body(createdBook);
    }

    @Operation(summary = "Update an existing book", description = "Edit a book details")
    @PutMapping("/{id}")
    public ResponseEntity<Book> update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateBookDto updateBookDto
            ) {

        Book existingBook = bookService.get(id);

        if (existingBook == null) {
            return ResponseEntity.notFound().build();
        }

        Book updatedBook = bookMapper.updateBookDtoToBook(updateBookDto);
        Book savedBook = bookService.update(existingBook,updatedBook);

        return ResponseEntity.created(URI.create("/books/" + savedBook.getId()))
                .body(savedBook);
    }
}