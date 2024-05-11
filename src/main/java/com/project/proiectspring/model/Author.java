package com.project.proiectspring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @NotNull
    private long id;

    @Column(name="last_name")
    @NotNull
    private String lastName;

    @Column(name="first_name")
    @NotNull
    private String firstName;

    @OneToOne(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
    private Biography biography;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("author")
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public Author() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public Author(String lastName, String firstName, Biography biography, List<Book> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.books = books;
    }

    public Author(long id, String lastName, String firstName, Biography biography, List<Book> books) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Biography getBiography() {
        return biography;
    }

    public void setBiography(Biography biography) {
        this.biography = biography;
    }
}