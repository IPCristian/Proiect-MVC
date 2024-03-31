package com.project.proiectspring.dto;

public class UpdateAuthorDto extends CreateAuthorDto {

    private long id;

    public UpdateAuthorDto() {
    }

    public UpdateAuthorDto(long id, String lastName, String firstName, String biography) {
        super(lastName,firstName,biography);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}