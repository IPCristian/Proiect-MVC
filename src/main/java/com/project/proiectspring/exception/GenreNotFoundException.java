package com.project.proiectspring.exception;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException() {
        super("This genre doesn't exist...");
    }
}
