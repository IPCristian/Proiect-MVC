package com.project.proiectspring.dto;

import com.project.proiectspring.model.Author;

public class UpdateBookDto extends CreateBookDto {

    private long id;

    public UpdateBookDto() {
    }

    public UpdateBookDto(String title, long author_id, long genre_id, long publisher_id, long id) {
        super(title, author_id, genre_id, publisher_id);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
