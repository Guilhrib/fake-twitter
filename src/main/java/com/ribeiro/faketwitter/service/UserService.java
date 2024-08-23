package com.ribeiro.faketwitter.service;

import com.ribeiro.faketwitter.controller.dto.CreateUser;
import com.ribeiro.faketwitter.controller.dto.UserResponse;
import com.ribeiro.faketwitter.entity.User;

import java.util.List;

public interface UserService {
    User findById(String userId);

    User findByUsername(String username);

    void create(CreateUser createUser);

    List<UserResponse> listUsers();
}
