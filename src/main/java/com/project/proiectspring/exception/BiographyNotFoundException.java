package com.project.proiectspring.exception;

public class BiographyNotFoundException extends RuntimeException{
    public BiographyNotFoundException() { super("Biography not found..."); }
}
