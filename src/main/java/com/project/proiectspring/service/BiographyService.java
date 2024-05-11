package com.project.proiectspring.service;

import com.project.proiectspring.exception.BiographyNotFoundException;
import com.project.proiectspring.model.Biography;
import com.project.proiectspring.repository.BiographyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BiographyService {

    private final BiographyRepository biographyRepository;

    public BiographyService(BiographyRepository biographyRepository) {
        this.biographyRepository = biographyRepository;
    }

    public Biography create(Biography biography) { return biographyRepository.save(biography); }

    public Biography update(Biography oldBiography, Biography biography)
    {
        Optional<Biography> existingBiography = biographyRepository.findById(oldBiography.getId());
        if (existingBiography.isEmpty())
        {
            throw new BiographyNotFoundException();
        }

        oldBiography.setBiography(biography.getBiography());
        oldBiography.setAuthor(biography.getAuthor());
        oldBiography.setBirthPlace(biography.getBirthPlace());

        return biographyRepository.save(oldBiography);
    }

    public List<Biography> get() {
        return biographyRepository.findAll();
    }

    public Biography get(Long id) {

        if (id != null)
        {
            Optional<Biography> biography = biographyRepository.findById(id);

            if (biography.isPresent()) {
                return biography.get();
            }
        }

        return null;
    }
}