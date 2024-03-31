package com.project.proiectspring.repository;


import com.project.proiectspring.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findByDescriptionContainingIgnoreCase(String description);

}