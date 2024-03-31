package com.project.proiectspring.mapper;

import com.project.proiectspring.dto.CreateRentalDto;
import com.project.proiectspring.dto.UpdateRentalDto;
import com.project.proiectspring.exception.BookNotFoundException;
import com.project.proiectspring.exception.UserNotFoundException;
import com.project.proiectspring.model.Book;
import com.project.proiectspring.model.BookRental;
import com.project.proiectspring.model.User;
import com.project.proiectspring.repository.BookRepository;
import com.project.proiectspring.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public RentalMapper(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public BookRental createRentalDtoToRental(CreateRentalDto createRentalDto)
    {
        User user = userRepository.findById(createRentalDto.getUser_id())
                .orElseThrow(UserNotFoundException::new);

        Book book = bookRepository.findById(createRentalDto.getBook_id())
                .orElseThrow(BookNotFoundException::new);

        BookRental rental = new BookRental();

        rental.setBook(book);
        rental.setUser(user);
        rental.setDueDate(createRentalDto.getDueDate());
        rental.setRentingDate(createRentalDto.getRentalDate());

        return rental;
    }

    public BookRental updateRentalDtoToRental(UpdateRentalDto updateRentalDto)
    {
        User user = userRepository.findById(updateRentalDto.getUser_id())
                .orElseThrow(UserNotFoundException::new);

        Book book = bookRepository.findById(updateRentalDto.getBook_id())
                .orElseThrow(BookNotFoundException::new);

        return new BookRental(
                updateRentalDto.getId(),
                user,
                book,
                updateRentalDto.getRentalDate(),
                updateRentalDto.getDueDate()
        );
    }
}
