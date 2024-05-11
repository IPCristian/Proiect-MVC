package com.project.proiectspring.repository;

import com.project.proiectspring.model.Author;
import com.project.proiectspring.model.Biography;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BiographyRepository extends JpaRepository<Biography, Long> {

    Optional<Biography> findById(Author author);

}
