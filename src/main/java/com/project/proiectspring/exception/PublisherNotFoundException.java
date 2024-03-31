package com.project.proiectspring.exception;

public class PublisherNotFoundException extends RuntimeException {
    public PublisherNotFoundException() {
        super("The publisher doesn't exist...");
    }
}