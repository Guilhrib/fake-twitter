package com.ribeiro.faketwitter.exception;

public class InternalErrorException extends RuntimeException {
    public InternalErrorException() {
        super("internal server error");
    }
}
