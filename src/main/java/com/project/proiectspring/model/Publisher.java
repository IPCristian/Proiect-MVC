package com.project.proiectspring.model;

import jakarta.persistence.*;

@Entity
@Table
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String location;
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
