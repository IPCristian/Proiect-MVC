package com.project.proiectspring.ServicesUnitTests;

import com.project.proiectspring.exception.GenreNotFoundException;
import com.project.proiectspring.model.Genre;
import com.project.proiectspring.repository.GenreRepository;
import com.project.proiectspring.service.GenreService;
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
class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService genreService;

    @Test
    void testCreate() {
        Genre genre = new Genre();
        when(genreRepository.save(genre)).thenReturn(genre);

        Genre result = genreService.create(genre);
        assertEquals(genre, result);
    }

    @Test
    void testUpdate() {
        Genre existingGenre = new Genre();
        when(genreRepository.findById(existingGenre.getId())).thenReturn(Optional.of(existingGenre));
        when(genreRepository.save(existingGenre)).thenReturn(existingGenre);

        Genre result = genreService.update(existingGenre);
        assertEquals(existingGenre, result);
    }

    @Test
    void testGenreNotFound() {
        Genre nonExistingGenre = new Genre();
        when(genreRepository.findById(nonExistingGenre.getId())).thenReturn(Optional.empty());

        assertThrows(GenreNotFoundException.class, () -> genreService.update(nonExistingGenre));
    }

    @Test
    void testGetByDescription() {
        String description = "Mystery";
        List<Genre> expectedGenres = new ArrayList<>();
        when(genreRepository.findByDescriptionContainingIgnoreCase(description)).thenReturn(expectedGenres);

        List<Genre> result = genreService.get(description);
        assertEquals(expectedGenres, result);
    }

    @Test
    void testGetAll() {
        List<Genre> expectedGenres = Collections.singletonList(new Genre());
        when(genreRepository.findAll()).thenReturn(expectedGenres);

        List<Genre> result = genreService.get(null);
        assertEquals(expectedGenres, result);
    }
}
