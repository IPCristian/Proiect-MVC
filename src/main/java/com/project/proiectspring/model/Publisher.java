package com.project.proiectspring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "publisher")
public class Publisher {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="location")
    @NotNull
    private String location;

    @Column(name="website")
    @NotNull
    private String website;

    public Publisher() {
    }

    public Publisher(String name, String location, String website) {
        this.name = name;
        this.location = location;
        this.website = website;
    }

    public Publisher(long id, String name, String location, String website) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.website = website;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
