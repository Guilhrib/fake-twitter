package com.ribeiro.faketwitter.controller;

import com.ribeiro.faketwitter.controller.dto.LoginRequest;
import com.ribeiro.faketwitter.controller.dto.LoginResponse;
import com.ribeiro.faketwitter.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    private final LoginService loginService;

    public TokenController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        var response = loginService.authenticate(request);
        return ResponseEntity.ok().body(response);
    }
}
