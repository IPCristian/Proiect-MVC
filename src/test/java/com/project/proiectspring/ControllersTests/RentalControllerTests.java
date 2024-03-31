package com.project.proiectspring.ControllersTests;

import com.project.proiectspring.controller.RentalController;
import com.project.proiectspring.dto.CreateRentalDto;
import com.project.proiectspring.mapper.RentalMapper;
import com.project.proiectspring.model.BookRental;
import com.project.proiectspring.model.User;
import com.project.proiectspring.service.BookRentalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalControllerTest {

    @Mock
    private BookRentalService bookRentalService;

    @Mock
    private RentalMapper rentalMapper;

    @InjectMocks
    private RentalController rentalController;

    @Test
    void testGet() {
        User user = new User();
        when(bookRentalService.get(user)).thenReturn(Collections.singletonList(new BookRental()));

        List<BookRental> result = rentalController.get(user);
        assertEquals(1, result.size());
    }

    @Test
    void testCreate() {
        CreateRentalDto createRentalDto = new CreateRentalDto();
        BookRental rental = new BookRental();
        when(rentalMapper.createRentalDtoToRental(createRentalDto)).thenReturn(rental);
        when(bookRentalService.create(rental)).thenReturn(rental);

        ResponseEntity<BookRental> responseEntity = rentalController.create(createRentalDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
}
