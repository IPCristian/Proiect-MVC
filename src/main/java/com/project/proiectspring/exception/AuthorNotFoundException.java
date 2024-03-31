package com.project.proiectspring.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException() {
        super("The author doesn't exist...");
    }
}
