package com.project.proiectspring.dto;

import java.sql.Date;

public class UpdateRentalDto extends CreateRentalDto {

    private long id;

    public UpdateRentalDto() {
    }

    public UpdateRentalDto(long id, long book_id, long user_id, Date rentalDate, Date dueDate) {
        super(book_id, user_id, rentalDate, dueDate);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
