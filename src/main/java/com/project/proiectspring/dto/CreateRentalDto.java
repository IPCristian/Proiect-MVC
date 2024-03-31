package com.project.proiectspring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class CreateRentalDto {

    @NotNull
    private long book_id;

    @NotNull
    private long user_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rentalDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    public CreateRentalDto() {
    }

    public CreateRentalDto(long book_id, long user_id, Date rentalDate, Date dueDate) {
        this.book_id = book_id;
        this.user_id = user_id;
        this.rentalDate = rentalDate;
        this.dueDate = dueDate;
    }
    ;

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
