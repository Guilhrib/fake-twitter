package com.ribeiro.faketwitter.exception;

public class ResourceAlreadyExists extends RuntimeException{
    public ResourceAlreadyExists(String resource) {
        super("resource %s already exists".formatted(resource));
    }
}
