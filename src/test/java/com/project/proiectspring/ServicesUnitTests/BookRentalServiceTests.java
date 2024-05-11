package com.project.proiectspring.ServicesUnitTests;

import com.project.proiectspring.exception.RentalNotFoundException;
import com.project.proiectspring.model.Book;
import com.project.proiectspring.model.BookRental;
import com.project.proiectspring.model.User;
import com.project.proiectspring.repository.RentalRepository;
import com.project.proiectspring.service.BookRentalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookRentalServiceTest {

    @Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private BookRentalService bookRentalService;

    @Test
    void testCreate() {
        BookRental bookRental = new BookRental();
        when(rentalRepository.save(bookRental)).thenReturn(bookRental);

        BookRental result = bookRentalService.create(bookRental);
        assertEquals(bookRental, result);
    }

    @Test
    void testUpdate() {
        BookRental existingRental = new BookRental();
        when(rentalRepository.findById(existingRental.getId())).thenReturn(Optional.of(existingRental));
        when(rentalRepository.save(existingRental)).thenReturn(existingRental);

        BookRental result = bookRentalService.update(existingRental);
        assertEquals(existingRental, result);
    }

    @Test
    void testRentalNotFound() {
        BookRental nonExistingRental = new BookRental();
        when(rentalRepository.findById(nonExistingRental.getId())).thenReturn(Optional.empty());

        assertThrows(RentalNotFoundException.class, () -> bookRentalService.update(nonExistingRental));
    }

    @Test
    void testGetByUser() {
        User user = new User();
        List<BookRental> expectedRentals = new ArrayList<>();
        when(rentalRepository.findByUser(user)).thenReturn(expectedRentals);

        List<BookRental> result = bookRentalService.get(user);
        assertEquals(expectedRentals, result);
    }

    @Test
    void testGetByBook() {
        Book book = new Book();
        List<BookRental> expectedRentals = new ArrayList<>();
        when(rentalRepository.findByBook(book)).thenReturn(expectedRentals);

        List<BookRental> result = bookRentalService.get(book);
        assertEquals(expectedRentals, result);
    }

    @Test
    void testGetAll() {
        List<BookRental> expectedRentals = Collections.singletonList(new BookRental());
        when(rentalRepository.findAll()).thenReturn(expectedRentals);

        List<BookRental> result = bookRentalService.get((User) null);
        assertEquals(expectedRentals, result);
    }
}