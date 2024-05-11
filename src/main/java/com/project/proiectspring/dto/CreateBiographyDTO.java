package com.project.proiectspring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateBiographyDTO {

    @NotBlank
    @Size(max = 1000)
    private String biography;

    @NotBlank
    @Size(max = 100)
    private String birthPlace;

    @NotNull
    private long author_id;

    public CreateBiographyDTO() {
    }

    public CreateBiographyDTO(String biography, String birthPlace, long author_id) {
        this.biography = biography;
        this.birthPlace = birthPlace;
        this.author_id = author_id;
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

    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }
}
