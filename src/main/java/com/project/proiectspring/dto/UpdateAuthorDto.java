package com.project.proiectspring.dto;

import com.project.proiectspring.model.Book;

import java.util.List;

public class UpdateAuthorDto extends CreateAuthorDto {

    private long id;

    public UpdateAuthorDto() {
    }

    public UpdateAuthorDto(long id, String lastName, String firstName) {
        super(lastName,firstName);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}