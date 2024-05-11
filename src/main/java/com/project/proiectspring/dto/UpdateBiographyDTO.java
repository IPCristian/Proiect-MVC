package com.project.proiectspring.dto;

public class UpdateBiographyDTO extends CreateBiographyDTO {

    private long id;

    public UpdateBiographyDTO() {
    }

    public UpdateBiographyDTO(String biography, String birthPlace, long author_id, long id) {
        super(biography, birthPlace, author_id);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
