package com.project.proiectspring.service;

import com.project.proiectspring.exception.RentalNotFoundException;
import com.project.proiectspring.model.Book;
import com.project.proiectspring.model.BookRental;
import com.project.proiectspring.model.User;
import com.project.proiectspring.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookRentalService {

    private final RentalRepository rentalRepository;

    public BookRentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public BookRental create(BookRental bookRental) { return rentalRepository.save(bookRental); }

    public BookRental update(BookRental bookRental) {
        Optional<BookRental> existingRental = rentalRepository.findById(bookRental.getId());
        if (existingRental.isEmpty()) {
            throw new RentalNotFoundException();
        }
        return rentalRepository.save(bookRental);
    }

    public List<BookRental> get(User user) {

        List<BookRental>  rentals = new ArrayList<>();

        if (user != null) {
            rentals = rentalRepository.findByUser(user);
        } else {
            rentals = rentalRepository.findAll();
        }

        return rentals;
    }

    public List<BookRental> get(Book book) {

        List<BookRental>  rentals = new ArrayList<>();

        if (book != null) {
            rentals = rentalRepository.findByBook(book);
        } else {
            rentals = rentalRepository.findAll();
        }

        return rentals;
    }
}
