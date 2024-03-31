package com.project.proiectspring.mapper;

import com.project.proiectspring.dto.CreateGenreDto;
import com.project.proiectspring.dto.UpdateGenreDto;
import com.project.proiectspring.model.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public Genre createGenreDtoToGenre(CreateGenreDto createGenreDto)
    {
        return new Genre(
                createGenreDto.getName(),
                createGenreDto.getDescription()
        );
    }

    public Genre updateGenreDtoToGenre(UpdateGenreDto updateGenreDto)
    {
        return new Genre(
                updateGenreDto.getId(),
                updateGenreDto.getName(),
                updateGenreDto.getDescription()
        );
    }
}
