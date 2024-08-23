package com.ribeiro.faketwitter.controller.dto;

import java.util.List;

public record UserList(
        Long userId,
        String username,
        List<String> roles
) {
}
