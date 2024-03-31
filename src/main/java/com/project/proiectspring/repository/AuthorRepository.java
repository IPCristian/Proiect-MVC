package com.project.proiectspring.repository;

import com.project.proiectspring.model.Author;
import com.project.proiectspring.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByLastName(String lastName);

    List<Author> findByFirstName(String firstName);

    List<Author> findByBiography(String biography);

}