package com.ribeiro.faketwitter.controller.dto;

public record LoginRequest(
        String username,
        String password
) {
}
