package com.project.proiectspring.mapper;

import com.project.proiectspring.dto.CreateBookDto;
import com.project.proiectspring.dto.UpdateBookDto;
import com.project.proiectspring.exception.AuthorNotFoundException;
import com.project.proiectspring.exception.GenreNotFoundException;
import com.project.proiectspring.exception.PublisherNotFoundException;
import com.project.proiectspring.model.Author;
import com.project.proiectspring.model.Book;
import com.project.proiectspring.model.Genre;
import com.project.proiectspring.model.Publisher;
import com.project.proiectspring.repository.AuthorRepository;
import com.project.proiectspring.repository.GenreRepository;
import com.project.proiectspring.repository.PublisherRepository;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;

    public BookMapper(AuthorRepository authorRepository, GenreRepository genreRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.publisherRepository = publisherRepository;
    }

    public Book createBookDtoToBook(CreateBookDto createBookDto)
    {
        Author author = authorRepository.findById(createBookDto.getAuthor_id())
                .orElseThrow(AuthorNotFoundException::new);

        Genre genre = genreRepository.findById(createBookDto.getGenre_id())
                .orElseThrow(GenreNotFoundException::new);

        Publisher publisher = publisherRepository.findById(createBookDto.getPublisher_id())
                .orElseThrow(PublisherNotFoundException::new);

        Book book = new Book();

        book.setTitle(createBookDto.getTitle());
        book.setGenre(genre);
        book.setAuthor(author);
        book.setPublisher(publisher);

        return book;
    }

    public Book updateBookDtoToBook(UpdateBookDto updateBookDto)
    {
        Author author = authorRepository.findById(updateBookDto.getAuthor_id())
                .orElseThrow(AuthorNotFoundException::new);

        Genre genre = genreRepository.findById(updateBookDto.getGenre_id())
                .orElseThrow(GenreNotFoundException::new);

        Publisher publisher = publisherRepository.findById(updateBookDto.getPublisher_id())
                .orElseThrow(PublisherNotFoundException::new);

        return new Book(
                updateBookDto.getId(),
                updateBookDto.getTitle(),
                author,
                genre,
                publisher
        );
    }
}