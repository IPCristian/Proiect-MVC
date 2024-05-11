package com.project.proiectspring.IntegrationTests;

import com.project.proiectspring.exception.BiographyNotFoundException;
import com.project.proiectspring.model.Author;
import com.project.proiectspring.model.Biography;
import com.project.proiectspring.repository.AuthorRepository;
import com.project.proiectspring.service.BiographyService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/schema.sql")
@Sql("/data.sql")
@Transactional
public class BiographyServiceTests {

    @Autowired
    private BiographyService biographyService;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void testCreateBiography() {

        // The 11th Author doesn't have a biography in the data.sql file
        Author author = authorRepository.findById(11L).orElse(null);
        assertNotNull(author);

        Biography biography = new Biography(author,"Test Biography", "Test Birth Place");
        Biography createdBiography = biographyService.create(biography);
        assertNotNull(createdBiography.getId());
        assertEquals("Test Biography", createdBiography.getBiography());
        assertEquals("Test Birth Place", createdBiography.getBirthPlace());
    }

    @Test
    void testUpdateBiography() {
        Biography existingBiography = biographyService.get(1L);
        assertNotNull(existingBiography);

        existingBiography.setBiography("Updated Biography");
        existingBiography.setBirthPlace("Updated Birth Place");

        Biography updatedBiography = biographyService.update(existingBiography, existingBiography);
        assertEquals("Updated Biography", updatedBiography.getBiography());
        assertEquals("Updated Birth Place", updatedBiography.getBirthPlace());
    }

    @Test
    void testGetAllBiographies() {
        List<Biography> biographies = biographyService.get();
        assertEquals(10, biographies.size());
    }

    @Test
    void testGetBiographyById() {
        Biography biography = biographyService.get(1L);
        assertNotNull(biography);
        assertEquals("Mihai Eminescu was a Romanian poet, novelist, and journalist, often regarded as the most famous and influential Romanian poet.", biography.getBiography());
    }

    @Test
    void testGetNonExistingBiography() {
        Biography biography = biographyService.get(15L);
        assertNull(biography);
    }

    @Test
    void testUpdateNonExistingBiography() {
        assertThrows(BiographyNotFoundException.class, () -> {
            Biography nonExistingBiography = new Biography(null,"Non Existing Biography", "Non Existing Birth Place");
            biographyService.update(nonExistingBiography, nonExistingBiography);
        });
    }
}
