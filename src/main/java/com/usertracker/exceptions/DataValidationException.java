package com.usertracker.exceptions;

public class DataValidationException extends RuntimeException{
    public DataValidationException(final String message) {
        super(message);
    }
}
