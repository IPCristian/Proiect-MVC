package com.project.proiectspring.controller;

import com.project.proiectspring.dto.CreateAuthorDto;
import com.project.proiectspring.dto.UpdateAuthorDto;
import com.project.proiectspring.mapper.AuthorMapper;
import com.project.proiectspring.model.Author;
import com.project.proiectspring.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    public AuthorController(AuthorService authorService, AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @Operation(summary = "Get all authors", description = "Retrieve information regarding all authors from the database")
    @GetMapping
    public List<Author> get(
            @RequestParam(required = true)
            String lastName) {

        return authorService.get(lastName);
    }

    @Operation(summary = "Add a new author", description = "Add a new author by providing details such as the name and biography")
    @PostMapping
    public ResponseEntity<Author> create(
            @RequestBody
            @Valid
            CreateAuthorDto createAuthorDto
    ) {
        Author author = authorMapper.createAuthorDtoToAuthor(createAuthorDto);
        Author createdAuthor = authorService.create(author);
        return ResponseEntity.created(URI.create("/authors/" + createdAuthor.getId()))
                .body(createdAuthor);
    }

    @Operation(summary = "Update an existing author", description = "Update an existing author with the provided details")
    @Parameter(name = "id", description = "Author ID", in = ParameterIn.PATH, required = true)
    @PutMapping("/{id}")
    public ResponseEntity<Author> update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateAuthorDto updateAuthorDto
            ) {
        Author existingAuthor = authorService.get(id);

        if (existingAuthor == null) {
            return ResponseEntity.notFound().build();
        }

        Author updatedAuthor = authorMapper.updateAuthorDtoToAuthor(updateAuthorDto);
        Author savedAuthor = authorService.update(existingAuthor,updatedAuthor);

        return ResponseEntity.created(URI.create("/authors/" + savedAuthor.getId()))
                .body(savedAuthor);
    }
}