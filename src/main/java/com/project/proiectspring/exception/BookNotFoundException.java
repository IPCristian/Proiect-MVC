package com.project.proiectspring.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("The book doesn't exist...");
    }
}
