package com.project.proiectspring.repository;

import com.project.proiectspring.model.Book;
import com.project.proiectspring.model.BookRental;
import com.project.proiectspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<BookRental, Long> {

    List<BookRental> findByUser(User user);

    List<BookRental> findByBook(Book book);

}
