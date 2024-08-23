package com.ribeiro.faketwitter.service;

import com.ribeiro.faketwitter.controller.dto.LoginRequest;
import com.ribeiro.faketwitter.controller.dto.LoginResponse;

public interface LoginService {
    LoginResponse authenticate(LoginRequest request);
}
