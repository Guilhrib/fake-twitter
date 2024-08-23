package com.ribeiro.faketwitter.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("username/login are invalid");
    }
}
