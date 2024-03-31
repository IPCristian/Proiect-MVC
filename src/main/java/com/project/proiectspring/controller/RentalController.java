package com.project.proiectspring.controller;

import com.project.proiectspring.dto.CreateRentalDto;
import com.project.proiectspring.mapper.RentalMapper;
import com.project.proiectspring.model.Book;
import com.project.proiectspring.model.BookRental;
import com.project.proiectspring.model.User;
import com.project.proiectspring.service.BookRentalService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final BookRentalService bookRentalService;
    private final RentalMapper rentalMapper;

    public RentalController(BookRentalService bookRentalService, RentalMapper rentalMapper) {
        this.bookRentalService = bookRentalService;
        this.rentalMapper = rentalMapper;
    }

    @Operation(summary = "Get all rentals", description = "Retrieve all of the existing rentals")
    @GetMapping
    public List<BookRental> get(
            @RequestParam(required = false)
            User user) {

        return bookRentalService.get(user);
    }

    @Operation(summary = "Add a new rental", description = "Create a new renting period for a book to a certain user")
    @PostMapping
    public ResponseEntity<BookRental> create(
            @RequestBody
            @Valid
            CreateRentalDto createRentalDto
    ) {
        BookRental rental = rentalMapper.createRentalDtoToRental(createRentalDto);
        BookRental createdRental = bookRentalService.create(rental);
        return ResponseEntity.created(URI.create("/rentals/" + createdRental.getId()))
                .body(createdRental);
    }


}
