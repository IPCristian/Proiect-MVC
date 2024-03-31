package com.project.proiectspring.dto;

public class UpdateGenreDto extends CreateGenreDto {

    private long id;

    public UpdateGenreDto() {
    }

    public UpdateGenreDto(long id, String name, String description) {
        super(name, description);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
