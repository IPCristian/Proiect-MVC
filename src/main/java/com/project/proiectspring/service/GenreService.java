package com.project.proiectspring.service;

import com.project.proiectspring.exception.GenreNotFoundException;
import com.project.proiectspring.model.Genre;
import com.project.proiectspring.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre update(Genre genre) {
        Optional<Genre> existingGenre = genreRepository.findById(genre.getId());
        if (existingGenre.isEmpty()) {
            throw new GenreNotFoundException();
        }
        return genreRepository.save(genre);
    }

    public List<Genre> get(String description) {
        List<Genre> genres = new ArrayList<>();

        if (description != null)
        {
            genres = genreRepository.findByDescriptionContainingIgnoreCase(description);
        } else {
            genres = genreRepository.findAll();
        }

        return genres;
    }
}
