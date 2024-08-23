package com.ribeiro.faketwitter.controller.dto;

import com.ribeiro.faketwitter.entity.Role;
import com.ribeiro.faketwitter.entity.User;

import java.util.List;

public record UserResponse(
        String userId,
        String username,
        List<String> roles
) {
    public UserResponse(User user) {
        this(
            user.getUserId().toString(),
            user.getUsername(),
            user.getRoles().stream().map(Role::getName).toList()
        );
    }
}
