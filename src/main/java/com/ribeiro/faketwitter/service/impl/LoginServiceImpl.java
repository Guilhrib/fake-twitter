package com.ribeiro.faketwitter.service.impl;

import com.ribeiro.faketwitter.controller.dto.LoginRequest;
import com.ribeiro.faketwitter.controller.dto.LoginResponse;
import com.ribeiro.faketwitter.entity.Role;
import com.ribeiro.faketwitter.entity.User;
import com.ribeiro.faketwitter.exception.InvalidCredentialsException;
import com.ribeiro.faketwitter.exception.ResourceNotFoundException;
import com.ribeiro.faketwitter.service.LoginService;
import com.ribeiro.faketwitter.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserService userService;
    private final JwtEncoder jwtEncoder;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginServiceImpl(
            UserService userService,
            JwtEncoder jwtEncoder,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.userService = userService;
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponse authenticate(LoginRequest request) {
        User user;
        try {
            user = userService.findByUsername(request.username());
        } catch (ResourceNotFoundException ex) {
            throw new InvalidCredentialsException();
        }

        if(!user.isLoginCorrect(request, passwordEncoder)) {
            throw new InvalidCredentialsException();
        }

        var scopes = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(";"));

        var now = Instant.now();
        var expiresIn = 300L;
        var claims = JwtClaimsSet.builder()
                .issuer("faketwiter")
                .subject(user.getUsername())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scopes", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new LoginResponse(jwtValue, expiresIn);
    }
}
