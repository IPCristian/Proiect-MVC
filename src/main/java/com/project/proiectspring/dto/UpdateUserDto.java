package com.project.proiectspring.dto;

public class UpdateUserDto extends CreateUserDto {

    private long id;

    public UpdateUserDto() {
    }

    public UpdateUserDto(long id, String lastName, String firstName, String email, String phoneNumber, String password) {
        super(lastName, firstName, email, phoneNumber, password);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
