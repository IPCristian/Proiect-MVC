package com.project.proiectspring.dto;

public class UpdatePublisherDto extends CreatePublisherDto {

    private long id;

    public UpdatePublisherDto() {
    }

    public UpdatePublisherDto(long id, String name, String location, String website) {
        super(name, location, website);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
