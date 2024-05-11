package com.project.proiectspring.mapper;

import com.project.proiectspring.dto.CreateBiographyDTO;
import com.project.proiectspring.dto.UpdateBiographyDTO;
import com.project.proiectspring.exception.AuthorNotFoundException;
import com.project.proiectspring.model.Author;
import com.project.proiectspring.model.Biography;
import com.project.proiectspring.repository.AuthorRepository;
import org.springframework.stereotype.Component;

@Component
public class BiographyMapper {

    private final AuthorRepository authorRepository;

    public BiographyMapper(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Biography createBiographyDtoToBiography(CreateBiographyDTO createBiographyDTO) {

        Author author = authorRepository.findById(createBiographyDTO.getAuthor_id())
                .orElseThrow(AuthorNotFoundException::new);

        if (author.getBiography() != null)
            throw new RuntimeException("Author's biography already exists...");

        Biography biography = new Biography();

        biography.setAuthor(author);
        biography.setBiography(createBiographyDTO.getBiography());
        biography.setBirthPlace(createBiographyDTO.getBirthPlace());

        return biography;
    }

    public Biography updateBiographyDtoToBiography(UpdateBiographyDTO updateBiographyDTO)
    {
        Author author = authorRepository.findById(updateBiographyDTO.getAuthor_id())
                .orElseThrow(AuthorNotFoundException::new);

        Biography biography = new Biography();

        biography.setAuthor(author);
        biography.setId(updateBiographyDTO.getId());
        biography.setBirthPlace(updateBiographyDTO.getBirthPlace());
        biography.setBiography(updateBiographyDTO.getBiography());

        return biography;
    }
}
