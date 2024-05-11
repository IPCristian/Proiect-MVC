package com.project.proiectspring.ControllersUnitTests;

import com.project.proiectspring.controller.GenreController;
import com.project.proiectspring.dto.CreateGenreDto;
import com.project.proiectspring.mapper.GenreMapper;
import com.project.proiectspring.model.Genre;
import com.project.proiectspring.service.GenreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenreControllerTest {

    @Mock
    private GenreService genreService;

    @Mock
    private GenreMapper genreMapper;

    @InjectMocks
    private GenreController genreController;

    @Test
    void testGet() {
        when(genreService.get(null)).thenReturn(Collections.singletonList(new Genre()));
        List<Genre> result = genreController.get(null);

        assertEquals(1, result.size());
    }

    @Test
    void testCreate() {
        CreateGenreDto createGenreDto = new CreateGenreDto();
        Genre genre = new Genre();
        when(genreMapper.createGenreDtoToGenre(createGenreDto)).thenReturn(genre);
        when(genreService.create(genre)).thenReturn(genre);

        ResponseEntity<Genre> responseEntity = genreController.create(createGenreDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
}

