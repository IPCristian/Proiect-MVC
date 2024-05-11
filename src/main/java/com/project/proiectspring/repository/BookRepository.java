package com.project.proiectspring.repository;

import com.project.proiectspring.model.Author;
import com.project.proiectspring.model.Book;
import com.project.proiectspring.model.Genre;
import com.project.proiectspring.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);

    List<Book> findByAuthorId(long authorId);

    List<Book> findByPublisher(Publisher publisher);

    List<Book> findByGenre(Genre genre);

}