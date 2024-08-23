package com.ribeiro.faketwitter.controller.dto;

public record LoginResponse(
        String accessToken,
        Long expiresIn
) {
}
