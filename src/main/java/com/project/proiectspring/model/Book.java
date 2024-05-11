package com.project.proiectspring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @NotNull
    private long id;

    @Column(name="title")
    @NotNull
    private String title;
    @ManyToOne
    @JsonIgnoreProperties("books")
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JsonIgnoreProperties("books")
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @ManyToOne
    @JsonIgnoreProperties("books")
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    public Book() {
    }

    public Book(String title, Author author, Genre genre, Publisher publisher) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
    }

    public Book(long id, String title, Author author, Genre genre, Publisher publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}