package com.ribeiro.faketwitter.controller.dto;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        String message,
        int code,
        HttpStatus statusCode
) {
}
