package com.project.proiectspring.exception;

public class RentalNotFoundException extends RuntimeException {
    public RentalNotFoundException() {
        super("This rental doesn't exist...");
    }
}
