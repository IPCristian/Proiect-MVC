package com.project.proiectspring.service;

import com.project.proiectspring.exception.BookNotFoundException;
import com.project.proiectspring.model.Author;
import com.project.proiectspring.model.Book;
import com.project.proiectspring.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Book book, Book newBook) {
        Optional<Book> existingBook = bookRepository.findById(book.getId());
        if(existingBook.isEmpty()) {
            throw new BookNotFoundException();
        }

        book.setAuthor(newBook.getAuthor());
        book.setGenre(newBook.getGenre());
        book.setPublisher(newBook.getPublisher());
        book.setTitle(newBook.getTitle());

        return bookRepository.save(book);
    }

    public List<Book> get(Author author) {

        List<Book> books = new ArrayList<>();

        if(author != null) {
                books = bookRepository.findByAuthor(author);
            } else {
                books = bookRepository.findAll();
        }
        return books;
    }

    public Book get(Long id) {

        if(id != null) {
            Optional<Book> book = bookRepository.findById(id);

            if (book.isPresent()) {
                return book.get();
            }
        }

        return null;
    }
}
