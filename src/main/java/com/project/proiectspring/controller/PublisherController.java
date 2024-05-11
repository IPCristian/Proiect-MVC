package com.project.proiectspring.controller;

import com.project.proiectspring.dto.CreatePublisherDto;
import com.project.proiectspring.mapper.PublisherMapper;
import com.project.proiectspring.model.Publisher;
import com.project.proiectspring.service.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private static final Logger logger = LoggerFactory.getLogger(PublisherController.class);
    private final PublisherService publisherService;
    private final PublisherMapper publisherMapper;

    public PublisherController(PublisherService publisherService, PublisherMapper publisherMapper) {
        this.publisherService = publisherService;
        this.publisherMapper = publisherMapper;
    }

    @Operation(summary = "Get all publishers", description = "Retrieve all publishers from the database")
    @GetMapping
    public List<Publisher> get(
            @RequestParam(required = false)
            String name) {

        logger.info("Retrieving publishers");
        return publisherService.get(name);
    }

    @Operation(summary = "Add a new publisher", description = "Add a new publisher record from the provided details")
    @PostMapping
    public ResponseEntity<Publisher> create(
            @RequestBody
            @Valid
            CreatePublisherDto createPublisherDto
    ) {
        Publisher publisher = publisherMapper.createPublisherDtoToPublisher(createPublisherDto);
        Publisher createdPublisher = publisherService.create(publisher);

        logger.info("Adding a new publisher");
        return ResponseEntity.created(URI.create("/publishers/" + createdPublisher.getId()))
                .body(createdPublisher);
    }
}
