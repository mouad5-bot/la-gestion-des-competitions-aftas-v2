package com.example.aftas.exception;

public class EmailOrPasswordIncorrectException extends RuntimeException {

    public EmailOrPasswordIncorrectException(String message) {
        super(message);
    }
}