package com.project.proiectspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "biography")
public class Biography {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @NotNull
    private long id;

    @OneToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @JsonIgnore
    @NotNull
    private Author author;

    @Column(name="biography")
    @NotNull
    private String biography;

    @Column(name="birth_place")
    @NotNull
    private String birthPlace;

    public Biography() {
    }

    public Biography(long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Biography(Author author, String biography, String birthPlace) {
        this.author = author;
        this.biography = biography;
        this.birthPlace = birthPlace;
    }

    public Biography(long id, Author author, String biography, String birthPlace) {
        this.id = id;
        this.author = author;
        this.biography = biography;
        this.birthPlace = birthPlace;
    }

    public long getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }
}
