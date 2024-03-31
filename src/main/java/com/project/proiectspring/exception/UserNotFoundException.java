package com.project.proiectspring.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("The user doesn't exist...");
    }
}
