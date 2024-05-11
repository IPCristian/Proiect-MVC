package com.project.proiectspring.controller;

import com.project.proiectspring.dto.CreateBiographyDTO;
import com.project.proiectspring.mapper.BiographyMapper;
import com.project.proiectspring.model.Biography;
import com.project.proiectspring.service.BiographyService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/biographies")
public class BiographyController {

    private static final Logger logger = LoggerFactory.getLogger(BiographyController.class);

    private final BiographyService biographyService;
    private final BiographyMapper biographyMapper;

    public BiographyController(BiographyService biographyService, BiographyMapper biographyMapper) {
        this.biographyService = biographyService;
        this.biographyMapper = biographyMapper;
    }

    @GetMapping
    public List<Biography> get()
    {
        logger.info("Retrieving biographies");
        return biographyService.get();
    }

    @PostMapping
    public ResponseEntity<Biography> create(
            @RequestBody
            @Valid
            CreateBiographyDTO createBiographyDTO
    ) {
        Biography biography = biographyMapper.createBiographyDtoToBiography(createBiographyDTO);
        Biography createdBiography = biographyService.create(biography);

        logger.info("Adding a new biography");
        return ResponseEntity.created(URI.create("/biographies/" + createdBiography.getId()))
                .body(createdBiography);
    }
}
