package com.ribeiro.faketwitter.exception;

public class ResourceNotFoundException extends  RuntimeException {
    public ResourceNotFoundException(String resource) {
        super("resource %s not found".formatted(resource));
    }
}
