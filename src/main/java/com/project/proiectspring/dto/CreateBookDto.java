package com.project.proiectspring.dto;

import com.project.proiectspring.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateBookDto {

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotNull
    private long author_id;

    @NotNull
    private long genre_id;

    @NotNull
    private long publisher_id;

    public CreateBookDto() {
    }

    public CreateBookDto(String title, long author_id, long genre_id, long publisher_id) {
        this.title = title;
        this.author_id = author_id;
        this.genre_id = genre_id;
        this.publisher_id = publisher_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }

    public long getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(long genre_id) {
        this.genre_id = genre_id;
    }

    public long getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(long publisher_id) {
        this.publisher_id = publisher_id;
    }
}
